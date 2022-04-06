package com.proptechos.parser.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proptechos.parser.client.ProptechOSLdClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.rdf4j.rio.jsonld.JSONLDParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class JsonLdParser {

    public static final String BASE_REC_IRI = "https://w3id.org/rec/core";

    private static final ObjectMapper mapper = new ObjectMapper();

    @Value("${proptechos.api.base.url}")
    private String apiBaseUrl;

    private final ProptechOSLdClient client;

    public JsonLdParser(ProptechOSLdClient client) {
        this.client = client;
    }

    public void parseAxiomHierarchy(String axiomPath, String axiomId) {
        JSONLDParser parser = new JSONLDParser();
        EntityHierarchyHandler hierarchyHandler = new EntityHierarchyHandler(client, apiBaseUrl);
        parser.setRDFHandler(hierarchyHandler);
        InputStream targetStream = client.getResponseRootSingleStream(
            apiBaseUrl + axiomPath, axiomId);
        try {
            parser.parse(targetStream, BASE_REC_IRI);
        } catch (IOException e) {
            log.error("Failed to parse ");
        }
        writeResult(hierarchyHandler.getHierarchy());
    }

    private void writeResult(Object result) {
        try {
            String hierarchyString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(result);
            log.info("\n" + hierarchyString);
        } catch (JsonProcessingException e) {
            log.warn("Failed to write json", e);
        }
    }

}
