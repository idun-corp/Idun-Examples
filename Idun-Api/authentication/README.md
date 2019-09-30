
## Authentication in Idun REST API

Authentication in Idun uses OAuth 2.0 protocol.
It can be separated into two categories:
* interactive authentication (from UI, operated by user)
* application (daemon) authentication, for applications working w/o user interaction.

In both cases, the final goal is to obtain an Access Token, which is used in HTTP header for every call. 

Authentication is performed in Microsoft Identity Platform (former Active Directory (AD)), so the user need to have an account in Idun AD.
In case of application authentication, the application need to have it's own ID in Idun AD. 
After user (application) authenticate itself, it obtains a token in JWT format, which is then put into Authorization header
for every REST call to Idun REST API. It has to be prepended by Bearer clause:
```text
--header 'Authorization: Bearer XXXXXX_TOKEN_BODY_XXXXXXXXXX'  
```

### 1. Interactive authentication uses OAuth Implicit Grant flow.

For Web UI application that is interacting with the user, there is MSAL for Javascript Library provided by Microsoft,
that allows to perform authorization of the user in the frame of UI. 
In this case, the application (usually, web page) is acting on behalf of the user that was authenticated.
The library exists for several JS frameworks as well as framework-agnostic version.
Details can be found here:

https://github.com/AzureAD/microsoft-authentication-library-for-js

**NOTE: The Scope during the request must be set to 'Api.Use'.**
Depending on type of usage, the refreshing of the token is handled by MSAL library.

The example of how to use it can be found in examples folder.

In case of a web application when backend makes the call to Idun API (and therefore need to authorize),
a OAuth Grant Code flow can be used, which is supported by MSAL:

```text
https://docs.microsoft.com/en-us/azure/active-directory/develop/msal-authentication-flows#authorization-code
```
   


### 2. Application Authentication.

In case of this type of authorization, a 'Client Credentials' OAuth flow is used. The application has its own id and password, 
and uses it to obtain a token. 

The application that wants to use Idun API, need to have its own identity in Idun Active Directory, as opposite to impersonating a user and acting on its behalf, like in previous section.
This is done by Idun Admin once for each application.
After the creation of identity, the ID and Secret of the application is obtained.
The most straightforward and universal (language-independent) way for application to authenticate itself 
is directly using HTTP POST method on Microsoft authentication endpoint.

```text
POST /{tenant}/oauth2/v2.0/token HTTP/1.1           //Line breaks for clarity
Host: login.microsoftonline.com
Content-Type: application/x-www-form-urlencoded

client_id=535fb089-9ff3-47b6-9bfb-4f1264799865
&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default
&client_secret=qWgdYAmab0YSkuL1qKv5bPX
&grant_type=client_credentials
``` 

important fields here are:
* client_id: ID of the application, obtained after its creation in Idun AD;
* client_secret: also generated after the application creation;
* scope: **this has to be application name followed by '/.default';**
* grant_type: indicates flow, must be 'client_credentials'.


Another way is to use MSAL Library provided by Microsoft. The example of how to use it can be found in examples folder.

The token provided has an expiration time. Usually it is one hour, and it is up to an application to track this timeout 
and/or expiration errors from server side. 

Current version is  '0.5.0-preview'   
Details how to use the lib in the project can be found here:
https://github.com/AzureAD/microsoft-authentication-library-for-java



## Migration from old Authentication method.

In previous version, Idun authentication was done by putting predefined Authorization code into Authorization header.
In a new version, the procedure is basically the same, the only difference is that the token is not statically defined, 
but obtained during the authentication process described above. 
Another difference is that the token is preceded by 'Bearer ' keyword in Authorization header.
Also, in case of 'Daemon application', the token refreshment must be coded and daemon must take care that the token is not expired.
Additionally, proper error handling in case the token validation failed, must be performed.
   
In case of MSAL libraries for Javascript (Angular or other frameworks), the token refreshment is performed by the library itself.

## Resources

For more information about Microsoft Identity Platform, OAuth2 and MSAL Libraries for different languages, it is recommended to 
visit Microsoft official documentation

```text
https://docs.microsoft.com/en-us/azure/active-directory/develop/authentication-scenarios
``` 