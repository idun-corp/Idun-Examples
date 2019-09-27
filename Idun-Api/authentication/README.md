Authentication in Idun uses OAuth 2.0 protocol.
It can be separated into two categories:
- interactive authentication (from UI, operated by user)
- application (daemon) authentication, for applications working w/o user interaction.

In both cases, the final goal is to obtain an Access Token, which is used in HTTP header for every call. 

Authentication is performed in Microsoft Identity Platform (former Active Directory (AD)), so the user need to have an account in Idun AD.
In case of application authentication, the application need to have it's own ID in Idun AD. 
After user (application) authenticate itself, it obtains a token in JWT format, which is then put into Authorization header
for every REST call to Idun REST API. It has to be prepended by Bearer clause:
--header 'Authorization: Bearer XXXXXX_TOKEN_BODY_XXXXXXXXXX'  

1. Interactive authentication uses OAuth Authorization Code flow.

For Web UI application that is interacting with the user, there is MSAL for Javascript Library provided by Microsoft,
that allows to perform authorization of the user in the frame of UI. It exists for several JS frameworks as well as framework-agnostic version.
Details can be found here:

https://github.com/AzureAD/microsoft-authentication-library-for-js

NOTE: The Scope during the request must be set to 'Api.Use'.

The example of how to use it can be found in examples folder.

2. Application Authorization.

In case of this type of authorization, a 'Client Credentials' OAuth flow is used. The application has its own id and password, 
and uses it to obtain a token. 

The application that wants to use Idun API, need to have its own identity in Idun Active Directory. This is done by Idun Admin once for each application.
After the creation of identity, the ID and Secret of the application is obtained.
The most straightforward and universal (language-independent) way for application to authenticate itself 
is directly using HTTP POST method on Microsoft authentication endpoint.

==================================================================================================
POST /{tenant}/oauth2/v2.0/token HTTP/1.1           //Line breaks for clarity
Host: login.microsoftonline.com
Content-Type: application/x-www-form-urlencoded

client_id=535fb089-9ff3-47b6-9bfb-4f1264799865
&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default
&client_secret=qWgdYAmab0YSkuL1qKv5bPX
&grant_type=client_credentials 
==================================================================================================

important fields here are:
client=_id: ID of the application, obtained after its creation in Idun AD;
client_secret: also generated after the application creation;
scope: this has to be application name followed by '/.default';
grant_type: indicates flow, must be 'client_credentials'.


Another way is to use MSAL Library provided by Microsoft. The example of how to use it can be found in examples folder.

Current version is  '0.5.0-preview'   
