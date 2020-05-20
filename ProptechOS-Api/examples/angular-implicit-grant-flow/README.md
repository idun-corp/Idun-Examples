# Angular example

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.0.1.

## Configuration

Open `environment.ts` file and fill the properties:

* _AUTHORITY_HREF_ = 'https://login.microsoftonline.com/<TENANT_ID>/'
* _CLIENT_ID_ = with your application client id
* _SCOPE_NAME_ = with application granted scope

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


