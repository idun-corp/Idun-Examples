![ProptechOS logo](../images/ProptechOS-logotype-ex.png)
# API


The resources and state of them are exposed via an endpoint each per top level class, with GET and POST methods for reading and creating/changing them. Please see [Open API Specification (Swagger) docs](https://proptechos.com/api/swagger-ui.html) for details and to try it out. (Note that if you are running a dedicated instance of ProptechOS, your api and your OAS will have a separate proptechos.com subdomain).

# Authentication

Authentication in ProptechOS uses OAuth 2.0 protocol.
It can be separated into two categories:
* interactive authentication (for applications accessing the API on behalf of user, like web apps and UIs)
* application (daemon) authentication, for applications working without user interaction.

See more in [the Authentication section](authentication)

# JSON and JSON-LD
The API follow RealEstateCore, which means JSON-LD and [hydra](https://hydra-cg.com). (link to JSON-LD parsers to be added here). However ProptechOS also includes plain JSON versions of all endpoints using the `/json/` prefix for the endpoints. E.g. the `/api/sensor` endpoint has a JSON version at `/api/json/sensor`.

Note that besides the endpoints and the contents being different, the different flavours of endpoints rely on different content type headers:

_JSON-LD_  
`type: application/ld+json`  
Using curl: `-H "accept: application/ld+json"`

_JSON_  
`type: application/json`  
Using curl: `-H "accept: application/json"`

# Aliases and AliasNamespaces

See more in [the Alias and AliasNamespace section](alias-aliasnamespace)

# Using the Swagger docs
Authentication step-by-step walkthrough
## 1. Click the Authorize button.
The padlock is unlocked, meaning you are not autorized.
![Swagger authentication step 1](../images/swagger-auth-1.png)

## 2. Click the checkbox
Do **NOT** change the client_id - leave it as is.
![Swagger authentication step 2](../images/swagger-auth-2.png)

## 3. Click Authorize
![Swagger authentication step 3](../images/swagger-auth-3.png)

## 4. Log in with your ProptechOS account
You will be taken to a Microsoft sign-in page.
If you are already signed in, this will not be needed, and if you are signed in with another Microsoft user, you might need to log out of that account, before signing in with your ProptechOS user.
If you do not have a ProptechOS user account, you will not be able to get authorized.
![Swagger authentication step 4](../images/swagger-auth-4.png)

## 5. Click close
You will be returned to the ProptechOS swagger doc.
You have now successfully autorized.
![Swagger authentication step 5](../images/swagger-auth-5.png)

## 6. Done.
The padlock is now locked, indicating that you are authorized.
![Swagger authentication step 6](../images/swagger-auth-6.png)

# Choosing the Property Owner for Swagger operations
## 1. Get the list of available Property Owners
![image](https://user-images.githubusercontent.com/16049329/112029331-cccc2c80-8b41-11eb-8db7-8dab3338e5dc.png)
![image](https://user-images.githubusercontent.com/16049329/112029456-eff6dc00-8b41-11eb-9d23-73dc9c9e307d.png)

## 2. Choose the Property Owner to use for requests
![image](https://user-images.githubusercontent.com/16049329/112029896-5ed43500-8b42-11eb-88b6-22c7e05c48a6.png)

## 3. Click Authorize.
![image](https://user-images.githubusercontent.com/16049329/112030128-9a6eff00-8b42-11eb-99d7-1afc117e7fac.png)

## 4. Insert Property Owner ID and click Authorize.
![image](https://user-images.githubusercontent.com/16049329/112030369-e0c45e00-8b42-11eb-98c7-a8e7ab0eedd7.png)

## 5. Click close.
![image](https://user-images.githubusercontent.com/16049329/112030579-15381a00-8b43-11eb-81e7-30aa56936c4a.png)

## 6. Done.
