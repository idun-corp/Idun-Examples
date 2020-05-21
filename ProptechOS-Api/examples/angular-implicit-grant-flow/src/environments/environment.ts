// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
export const protectedMap: [string, string[]][] = [
  ['https://idundev.proptechos.com/api/', ['https://idundev.proptechos.com/api/Api.Use']]
];

export const environment = {
  production: false,

  auth: {
    clientID : '3f557e2a-3076-42d7-9c81-d403551c8cf7',
    authority: 'https://login.microsoftonline.com/d4218456-670f-42ad-9f6a-885ae15b6645/',
    redirectUri: 'http://localhost:5200/dashboard',
    consentScopes: ['https://idundev.proptechos.com/api/Api.Use'],
    protectedResourceMap: protectedMap,
    unprotectedResources: []
  },
  baseUrl: 'https://idundev.proptechos.com/api'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
