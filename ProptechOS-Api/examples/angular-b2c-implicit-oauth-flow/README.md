# Angular B2C implicit OAuth2 flow example

This project illustrates how to implement authentication and authorization in the ProptechOS API using [Microsoft Authentication Library for Angular](https://www.npmjs.com/package/@azure/msal-angular) 

## Configuration

Open `environment.ts` file and fill the properties:

* CLIENT_ID = with your application client id
* PROPTECHOS_API_URL = 'https://....proptechos.com/api/...'
* PROPTECHOS_APPLICATION_ID_URI = 'https://...proptechos.onmicrosoft.com/...'

```javascript
const clientId = "<CLIENT_ID>";
const proptechOsApiUrl = "<PROPTECHOS_API_URL>";
const proptechOsApplicationIdUri = "<PROPTECHOS_APPLICATION_ID_URI>";

export const environment = {
  production: false,
  proptechOsApiUrl: proptechOsApiUrl,
  msalConfig: {
    auth: {
      clientId: clientId,
      redirectUri: location.origin,
      authority: `https://proptechos.b2clogin.com/proptechos.onmicrosoft.com/b2c_1_sign_in/`,
      knownAuthorities: ["proptechos.b2clogin.com"],
    },
    consentScopes: [`${proptechOsApplicationIdUri}/api.use`],
    protectedResourceMap: [
      [`${proptechOsApiUrl}/*`, [`${proptechOsApplicationIdUri}/api.use`]],
    ] as [string, string[]][],
  }
};
```

AppModule



## Run example

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
