# Node.js Sample Application

The intention of this simple application is to show the usage of Microsoft identity platform and the OAuth 2.0 client credentials flow. More about credentials flow can be found by [Microsoft identity platform and the OAuth 2.0 client credentials flow](https://docs.microsoft.com/en-us/azure/active-directory/develop/v2-oauth2-client-creds-grant-flow). 

## Configuration

Open `server.js` file and fill the properties:

* _AUTHORITY_HREF_ = 'https://login.microsoftonline.com/d4218456-670f-42ad-9f6a-885ae15b6645/oauth2/v2.0/token'
* _CLIENT_ID_ = with your application client id
* _CLIENT_SECRET_ = with your application client secret
* _SCOPE_NAME_ = with application granted scope

## Installation 

Run `npm install` from the root directory of the project.

## Development local server

Run `npm start` from the root directory of the project. 

Navigate to `http://localhost:3000/`. 
