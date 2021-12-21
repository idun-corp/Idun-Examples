# Node-RED Sample Application

The intention of this simple application is to show the usage of Microsoft identity platform and the OAuth 2.0 client credentials flow. More about credentials flow can be found by [Microsoft identity platform and the OAuth 2.0 client credentials flow](https://docs.microsoft.com/en-us/azure/active-directory/develop/v2-oauth2-client-creds-grant-flow). 

## Configuration

Open `client-credential.json` file and fill the properties:

* _<TENANT_ID>_ = `d4218456-670f-42ad-9f6a-885ae15b6645`
* _<CLIENT_ID>_ = your application client id
* _CLIENT_SECRET_ = your application client secret
* _SCOPE_NAME_ = application scope (e.g. `https://proptechos.com/api/api.use`)

## Installation 

Import `client-credential.json` into your Node-RED local installation.
