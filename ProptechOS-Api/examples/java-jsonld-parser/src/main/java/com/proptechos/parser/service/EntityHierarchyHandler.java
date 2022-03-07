package com.proptechos.parser.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proptechos.parser.client.ProptechOSLdClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.helpers.AbstractRDFHandler;
import org.eclipse.rdf4j.rio.jsonld.JSONLDParser;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.proptechos.parser.service.JsonLdParser.BASE_REC_IRI;

@Slf4j
public class EntityHierarchyHandler extends AbstractRDFHandler {

    private static final String GEN_ID_PREFIX = "genid-";

    private final List<String> subElementsRelations = List.of(
        "https://w3id.org/rec/core/hasSubBuildingComponent", "https://w3id.org/rec/core/hasSubDevice");

    private static final ObjectMapper mapper = new ObjectMapper();

    private final ProptechOSLdClient client;
    private final String apiBaseUrl;
    private final Map<String, Object> hierarchy;
    private final Map<String, Object> aliasMap;

    public EntityHierarchyHandler(ProptechOSLdClient client, String apiBaseUrl) {
        this.client = client;
        this.apiBaseUrl = apiBaseUrl;
        this.hierarchy = new HashMap<>();
        this.aliasMap = new HashMap<>();
    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        String subjectValue = st.getSubject().stringValue();
        String predicateValue = st.getPredicate().stringValue();
        String objectValue = st.getObject().stringValue();
        log.debug("Processing @id Subject IRI [{}]", subjectValue);
        log.debug("Processing Predicate IRI [{}]", predicateValue);
        log.debug("Processing Object IRI [{}]", objectValue);
        if (aliasMap.containsKey(subjectValue)) {
            Map<String, Object> aliasRel = (Map<String, Object>) aliasMap.get(subjectValue);
            if (StringUtils.hasLength(objectValue) && objectValue.contains(apiBaseUrl)) {
                aliasRel.put(predicateValue, objectValue);
            }
        } else {
            if (predicateValue.contains("http://proptechos.com/ontology/extension/hasAlias")) {
                ((Map<String, Object>)hierarchy.get(subjectValue))
                    .putIfAbsent(predicateValue, aliasMap);
                aliasMap.put(objectValue, new HashMap<>());
            }
            if (!subjectValue.contains(GEN_ID_PREFIX)) {
                hierarchy.putIfAbsent(subjectValue, new HashMap<String, Object>());

                if (StringUtils.hasLength(objectValue) && objectValue.contains(apiBaseUrl) && !hierarchy.containsKey(objectValue)) {
                    log.info("Processing Relation IRI [{}]", predicateValue);
                    if (!subElementsRelations.contains(predicateValue)) {
                        Map<String, Object> values = (Map<String, Object>) hierarchy.get(subjectValue);
                        InputStream dataStream = client.getResponseRootSingleStream(objectValue);
                        JSONLDParser parser = new JSONLDParser();
                        EntityHierarchyHandler nestedHandler = new EntityHierarchyHandler(client, apiBaseUrl);
                        parser.setRDFHandler(nestedHandler);
                        try {
                            parser.parse(dataStream, BASE_REC_IRI);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        values.put(predicateValue, nestedHandler.getHierarchy());
                    }
                }
            }
        }
    }

    public Map<String, Object> getHierarchy() {
        return hierarchy;
    }

}
