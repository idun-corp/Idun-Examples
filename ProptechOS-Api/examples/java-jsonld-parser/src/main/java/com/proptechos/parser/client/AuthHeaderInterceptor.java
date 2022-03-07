package com.proptechos.parser.client;

import com.microsoft.aad.msal4j.IAuthenticationResult;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;

public class AuthHeaderInterceptor implements ClientHttpRequestInterceptor {

    private final ClientAppAuthProvider provider;
    private String authToken;
    private Instant expirationTime;

    public AuthHeaderInterceptor(ClientAppAuthProvider provider) {
        this.provider = provider;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (!StringUtils.hasLength(authToken)
            || Instant.now().equals(expirationTime) || Instant.now().isAfter(expirationTime)) {
            IAuthenticationResult authResult = provider.authenticate();
            expirationTime = authResult.expiresOnDate().toInstant();
            authToken = authResult.accessToken();
        }

        request.getHeaders().add("Authorization", "Bearer " + authToken);

        return execution.execute(request, body);
    }
}
