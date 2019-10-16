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
 * using Client Id, Password, and Tenant Id
 * tenantId - id of Idun in AAD
 * clientId - id of the application (NOT a user) in Idun AD. Obtained from Idun administrator.
 * clientSecret - secret of the application in Idun AD
 */

public class MicrosoftADGraphClient {

    public String acquireTokenForGraphApiClientCreds(String tenantId, String clientId, String clientSecret)
            throws ExecutionException, InterruptedException, MalformedURLException {

        ConfidentialClientApplication app =
                ConfidentialClientApplication.builder(
                        clientId, ClientCredentialFactory.create(clientSecret))
                        .authority("https://login.microsoftonline.com/" + tenantId + "/")
                        .build();

        ClientCredentialParameters clientCredentialParameters =
                ClientCredentialParameters.builder(Collections.singleton("application-id/.default")).build();

        Future<IAuthenticationResult> future = app.acquireToken(clientCredentialParameters);

        return future.get().accessToken();
    }

}
