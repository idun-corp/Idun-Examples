package com.proptechos.parser.client;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class ClientAppAuthProvider {

    private final String appClientId;
    private final String appClientSecret;
    private final String appLoginUrl;
    private final String applicationScope;

    public ClientAppAuthProvider(String appClientId, String appClientSecret, String appLoginUrl, String applicationScope) {
        this.appClientId = appClientId;
        this.appClientSecret = appClientSecret;
        this.appLoginUrl = appLoginUrl;
        this.applicationScope = applicationScope;
    }

    public IAuthenticationResult authenticate() {
        try {
            log.info("Authorization client id '{}'", appClientId);
            ConfidentialClientApplication app = ConfidentialClientApplication
                .builder(appClientId, ClientCredentialFactory.createFromSecret(appClientSecret))
                .authority(appLoginUrl)
                .build();

            Set<String> scopes = new HashSet<>(
                Collections.singletonList(applicationScope));

            ClientCredentialParameters params = ClientCredentialParameters.builder(scopes).build();

            Future<IAuthenticationResult> future = app.acquireToken(params);
            return future.get();
        } catch (InterruptedException | ExecutionException | MalformedURLException e) {
            throw new IllegalStateException("Authentication failed");
        }
    }
}
