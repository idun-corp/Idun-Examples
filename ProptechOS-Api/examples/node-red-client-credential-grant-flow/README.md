# Node-RED Sample Application

The intention of this simple application is to show the usage of Microsoft identity platform and the OAuth 2.0 client credentials flow. More about credentials flow can be found by [Microsoft identity platform and the OAuth 2.0 client credentials flow](https://docs.microsoft.com/en-us/azure/active-directory/develop/v2-oauth2-client-creds-grant-flow). 

## Configuration

Open `client-credential.json` file and fill the properties:

* _<CLIENT_ID>_ = your application client id
* _<CLIENT_SECRET>_ = your application client secret
* _<SCOPE_NAME>_ = application scope (e.g. `https://proptechos.com/api/api.use`)
* _<BASE_URL>_ = ProptechOS API base url (e.g. `https://proptechos.com/api`)

## Installation 

Import `client-credential.json` into your Node-RED local installation.
