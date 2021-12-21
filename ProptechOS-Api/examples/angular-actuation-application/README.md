# Angular example

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.0.1.

## Configuration

Open `src/environments/environment.ts` file and fill the properties:

* _BASE_API_URL_ = ProptechOS api base url (e.g. `https://proptechos.com/api/`)
* _AUTHORITY_HREF_ = 'https://login.microsoftonline.com/d4218456-670f-42ad-9f6a-885ae15b6645/'
* _CLIENT_APP_ID_ = with your application client id
* _RESOURCE_SCOPES_ = with application scope (e.g. `https://proptechos.com/api/Api.Use`)

## Installation 
  
Run `npm install` from `./api` directory.

Run `npm install` from the root directory of the project.

## Development local server

Run `npm run start:local` for a dev UI server.

Run `npm run dev` from api directory for a dev back-end server. 

Navigate to `http://localhost:5200/`. 

The app will automatically reload if you change any of the source files.

## Build

### For Poduction environment
Run `npm build:prod` and `npm start:prod`.


