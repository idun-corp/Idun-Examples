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
