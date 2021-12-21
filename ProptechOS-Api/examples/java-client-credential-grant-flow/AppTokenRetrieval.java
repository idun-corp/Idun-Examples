package demo;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * this is an example of Access Token retrieval using MSAL Library and Client Credentials Flow
 * using CLIENT_ID, CLIENT_SECRTET, and Tenant Id
 * clientId - your application client id. Obtained from Idun administrator.
 * clientSecret - your application client secret. Obtained from Idun administrator.
 * authority - ProptechOS at Microsoft Azure: https://login.microsoftonline.com/d4218456-670f-42ad-9f6a-885ae15b6645/
 */

public class MicrosoftADGraphClient {

    public String acquireTokenForGraphApiClientCreds(String tenantId, String clientId, String clientSecret)
            throws ExecutionException, InterruptedException, MalformedURLException {

        ConfidentialClientApplication app =
                ConfidentialClientApplication.builder(
                            clientId, 
                            ClientCredentialFactory.createFromSecret(clientSecret))
                        .authority("https://login.microsoftonline.com/d4218456-670f-42ad-9f6a-885ae15b6645/")
                        .build();

        ClientCredentialParameters clientCredentialParameters =
                ClientCredentialParameters.builder(Collections.singleton("application-id/.default")).build();

        Future<IAuthenticationResult> future = app.acquireToken(clientCredentialParameters);

        return future.get().accessToken();
    }

}
