const clientId = "<CLIENT_ID>";
const proptechOsApiUrl = "<PROPTECHOS_API_URL>";
const proptechOpApiIdUri = "<PROPTECHOS_API_ID_URI>";

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
    consentScopes: [`${proptechOpApiIdUri}/api.use`],
    protectedResourceMap: [
      [`${proptechOsApiUrl}/*`, [`${proptechOpApiIdUri}/api.use`]],
    ] as [string, string[]][],
  }
};
