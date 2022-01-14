# Authentication
![ProptechOS logo](../../images/ProptechOS-logotype-ex.png)  
Authentication in ProptechOS uses OAuth 2.0 protocol.
It can be separated into two categories:
* Implicit flow (for applications accessing the API on behalf of user, like web apps and UIs)
* Client credentials flow (for application (daemon) authentication working without user interaction)

In both cases, the final goal is to obtain an Access Token, which is used in HTTP header for every subsequent call.

Authentication is performed in Microsoft Identity Platform (former Active Directory, AD), so the user need to have a ProptechOS account. In case of application authentication, the application need to be registered in ProptechOS and have it's own client id and client secret. After the user and/or application has authenticated itself, it obtains a token in JWT format, which is then put into the Authorization header for subsequent calls to the ProptechOS REST API.  
It has to be prepended by Bearer clause:
```
--header 'Authorization: Bearer XXXXXX_TOKEN_BODY_XXXXXXXXXX'  
```

## 1. Implicit Grant flow
**Interactive authentication using OAuth 2.0**

For Web UI application that users are interacting with, there is MSAL for Javascript Library provided by Microsoft,
that allows to perform authorization of the user in the frame of a UI. In this case, the application (usually, web page) is acting on behalf of the user that was authenticated.
The library exists for several JS frameworks as well as framework-agnostic version.
Details can be found here:
https://github.com/AzureAD/microsoft-authentication-library-for-js

Depending on type of usage, the refreshing of the token is handled by MSAL library.

The application that wants to use the ProptechOS API with Implicit flow authentication need to be registered in ProptechOS and have a Client ID and the correct Redirect URI registered. Registering the application and redirect URI is done by Idun admin.

Examples of how to use it can be found in the **[examples folder](../examples)**.


```
// Line breaks for legibility only
GET  https://login.microsoftonline.com/proptechos.onmicrosoft.com/oauth2/v2.0/authorize?
client_id={ client id}
&response_type=id_token
&redirect_uri={ http%3A%2F%2myApp.com%2Fmyredirectendpoint%2F }
&scope=https%3A%2F%2Fproptechos.com%2Fapi%2FApi.Use
&response_mode=fragment
&state=12345
&nonce=678910
```

important to note:
* client_id: ID of the application, obtained after Application registration by Idun **Replace with your client id**
* redirect_uri: upon Applicaiton registration for the implicit auth flow you will state your redirect uri. **Replace myapp.com/myredirectendpoint with your redirect endpoint**
* scope: **If you are using a dedicated instance** of ProptechOS add the subdomain of your instance to the scope e.g._'https%3A%2F%2F**mydedicatedinstance**.proptechos.com%2Fapi%2FAPI.use'_

Read more: [Microsoft Docs - MSAL Authentication Flows Authorization Code](https://docs.microsoft.com/en-us/azure/active-directory/develop/msal-authentication-flows#authorization-code)

## 2. Client Credential flow
**Application Authentication using OAuth 2.0**

In case of this type of authorization, a 'Client Credentials' OAuth flow is used. The application has its own id _and password_ ("secret"), and uses it to obtain a token.

The application that wants to use client credential flow need to be registered in ProptechOS. As opposed to Implicit auth (above) the application will _not need to register a Redirect URI_, but _need to ask for a Client Secret to be generated_. This is done by Idun Admin once for each application. After the creation of identity, the ID and Secret of the application is obtained.

The most straightforward and universal (language-independent) way for application to authenticate itself
is directly using HTTP POST method on Microsoft authentication endpoint.

```
//Line breaks for clarity
POST https://login.microsoftonline.com/proptechos.onmicrosoft.com/oauth2/v2.0/token?
client_id={ client ID }
&client_secret={ client secret }
&scope=https%3A%2F%2Fproptechos.com%2Fapi%2F.default
&grant_type=client_credentials
```

important to note:
* client_id: ID of the application, obtained after Application registration by Idun. **Replce with your client id**
* client_secret: also generated after Application registration by Idun **Replace with your client secret**
* scope: **If you are using a dedicated instance** of ProptechOS add the subdomain of your instance to the scope e.g._'https%3A%2F%2F**mydedicatedinstance**.proptechos.com%2Fapi%2F.default'_
* grant_type: must be 'client_credentials' (indicates which auth flow).

Examples of how to use it can be found in the **[examples folder](../examples)**.

Another way is to use MSAL Library provided by Microsoft. An example of that can also be found in the **[examples folder](../examples)**.

The token provided has an expiration time. Usually it is one hour, and it is up to an application to track this timeout
and/or expiration errors, and to retrieve new access tokens in time.

Current version is  '0.5.0-preview'   
Details how to use the lib in the project can be found here:
https://github.com/AzureAD/microsoft-authentication-library-for-java

## Migrating to ProptechOS v4.1 auth
**(From b2b to b2c based auth, January 2022)**  

With ProptechOS v4.1 the scope and login authority changes for both the authentication flows.
Also, your application's client id and client secret will be reset, so use the new credentials you will find in 1Password.

### Implicit flow
* scope  
changed from `https://proptechos.com/api/Api.Use` to:  
**`https://proptechos.onmicrosoft.com/multi/api/Api.Use`**  
or `https://proptechos.onmicrosoft.com/<< instance >>/api/Api.Use` if your are integrating to a dedicated instance.
* login authority  
changed from: `https://login.microsoftonline.com/d4218456-670f-42ad-9f6a-885ae15b6645/` to:  
**`https://proptechos.b2clogin.com/proptechos.onmicrosoft.com/b2c_1a_signup_signin/`**
* client id  
use new client id found in 1Password

### Client credential flow 
* scope  
changed from `https://proptechos.com/api/.default` to:  
**`https://proptechos.onmicrosoft.com/multi/api/.default`**  
or `https://proptechos.onmicrosoft.com/<< instance >>/api/.default` if you are integrating to a dedicated instance.  
* login authority  
changed from: `https://login.microsoftonline.com/d4218456-670f-42ad-9f6a-885ae15b6645/oauth2/v2.0/token` to:  
**`https://login.microsoftonline.com/proptechos.onmicrosoft.com/oauth2/v2.0/token`**
* client id  
use new client id found in 1Password
* client secret  
use new client secret found in 1Password

### Testing with preview instance
A dedicated instance "preview" which runs v4.1 has been setup where you test the new auth configs.

Here is the configs you would use to test auth with the preview instance:

#### Implicit flow
* scope `https://proptechos.onmicrosoft.com/preview/api/Api.Use`
* login authority: `https://proptechos.b2clogin.com/proptechos.onmicrosoft.com/b2c_1a_signup_signin/`
* client id: use new client id found in 1Password (same that will later work in v4.1 prod)
 
#### Client credential flow
* scope: `https://proptechos.onmicrosoft.com/preview/api/.default`
* login authority: `https://login.microsoftonline.com/proptechos.onmicrosoft.com/oauth2/v2.0/token`
* client id: new client id found in 1Password (same that will later work in v4.1 prod)
* client secret: use new client secret found in 1Password (same that will later work in v4.1 prod)

See the `b2c` auth examples:

* [.NET Core Console application](../examples/netcore-b2c-client-credential-flow-console-app) (client credential flow)
* [Angular application](../examples/angular-b2c-implicit-oauth-flow) (implicit flow)

## Property Owner
Each request to ProptechOS is scoped to a single Property Owner. If no Property Owner header is specified, the request will be scoped to the user's default Property Owner. The Property Owner scope can also be explicitly specified using the `X-Property-Owner` header (if the agent making the request has access to multiple Property Owners).

A request for the top 1 Real Estate, scoped to the Property Owner `43ea1afe-3412-4a65-987e-a615e2172cd4`.
```
// Line breaks for clarify
curl -X GET "https://proptechos.com/api/json/realestate?page=0&size=1" 
-H  "accept: application/json" 
-H  "Authorization: Bearer fjY0(...)kjMx-FdJX" 
-H  "X-Property-Owner: 43ea1afe-3412-4a65-987e-a615e2172cd4"
```
The only endpoints which is not scoped to a Property Owner, are
* `GET /propertyowner/{id}` Get a specific Proptery Owner
* `GET /propertyowner/default` Get the default Property Owner for the user making the request.
* `GET /propertyowner/` Get the Property Owners that the user making the request has access to.

## Obsolete
### Migration from pre-2019 Authentication method.

In previous version, Idun authentication was done by putting predefined Authorization code into Authorization header.
In a new version, the procedure is basically the same, the only difference is that the token is not statically defined,
but obtained during the authentication process described above.
Another difference is that the token is preceded by 'Bearer ' keyword in Authorization header.
Also, in case of 'Daemon application', the token refreshment must be coded and daemon must take care that the token is not expired.
Additionally, proper error handling in case the token validation failed, must be performed.

In case of MSAL libraries for Javascript (Angular or other frameworks), the token refreshment is performed by the library itself.

### Resources

For more information about Microsoft Identity Platform, OAuth2 and MSAL Libraries for different languages, it is recommended to
visit Microsoft official documentation

```text
https://docs.microsoft.com/en-us/azure/active-directory/develop/authentication-scenarios
```
