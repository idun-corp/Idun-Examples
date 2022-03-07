package com.proptechos.parser.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class ProptechOSLdClient {

    private final RestTemplate restTemplate;

    public ProptechOSLdClient(
        @Value("${azure.aad.clientId}") String clientId,
        @Value("${azure.aad.clientSecret}") String clientSecret,
        @Value("${azure.aad.user.login.url}") String authorityLink,
        @Value("${proptechos.api.application.scope}") String appScope,
//        @Value("${proptechos.api.base.url}") String baseurl,
        RestTemplateBuilder restTemplateBuilder) {

        ClientAppAuthProvider authProvider =
            new ClientAppAuthProvider(clientId, clientSecret, authorityLink, appScope);
        AuthHeaderInterceptor interceptor = new AuthHeaderInterceptor(authProvider);
        this.restTemplate =
            restTemplateBuilder
//                .rootUri(baseurl)
                .interceptors(interceptor)
                .errorHandler(new ClientResponseErrorHandler())
                .build();
    }

    public String getResponseRootSingle(String rootUrl, String rootId) {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(rootUrl + "/{id}", String.class, rootId);
            return responseEntity.getBody();
        } catch (Exception e) {
            throw new IllegalStateException("Object with id '" + rootId + "' not found.");
        }
    }

    public InputStream getResponseRootSingleStream(String rootUrl, String rootId) {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(rootUrl + "/{id}", String.class, rootId);
            return new ByteArrayInputStream(responseEntity.getBody().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new IllegalStateException("Object with id '" + rootId + "' not found.");
        }
    }

    public InputStream getResponseRootSingleStream(String rootUrl) {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(rootUrl, String.class);
            return new ByteArrayInputStream(responseEntity.getBody().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new IllegalStateException("Object with id not found.", e);
        }
    }
}
