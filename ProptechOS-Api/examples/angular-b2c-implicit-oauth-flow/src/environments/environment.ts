const authorityHref = "<AUTHORITY_HREF>";
const baseUrl = "<BASE_URL>";
const clientId = "<CLIENT_ID>";
const scopeName = "<SCOPE_NAME>";

export const environment = {
  production: false,
  baseUrl: baseUrl,
  msalConfig: {
    auth: {
      clientId: clientId,
      redirectUri: location.origin,
      authority: authorityHref,
      knownAuthorities: ["proptechos.b2clogin.com"],
    },
    consentScopes: [scopeName],
    protectedResourceMap: [
      [`${baseUrl}/*`, [scopeName]],
    ] as [string, string[]][],
  }
};
