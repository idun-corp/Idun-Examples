// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
import {Configuration} from 'msal';
import {MsalAngularConfiguration} from '@azure/msal-angular';

const protectedMap: Map<string, Array<string>> = new Map<string, Array<string>>();
protectedMap.set('<BASE_API_URL>', ['<RESOURCE_SCOPES>']);

const msalConfiguration: Configuration = {
  auth: {
    clientId : '<CLIENT_APP_ID>',
    authority: '<AUTHORITY_HREF>',
    redirectUri: 'http://localhost:5200/dashboard'
  },
  framework: {
    isAngular: true,
    protectedResourceMap: protectedMap,
    unprotectedResources: []
  }
};

const msalAngularConfiguration: MsalAngularConfiguration = {
  consentScopes: ['<RESOURCE_SCOPES>'],
  protectedResourceMap: protectedMap,
  unprotectedResources: []
};

export const environment: IEnvironment = {
  production: false,
  baseUrl: '<BASE_API_URL>',
  msalConfig: msalConfiguration,
  msalAngularConfig: msalAngularConfiguration
};

export interface IEnvironment {
  production: boolean;
  baseUrl: string;
  msalConfig: Configuration;
  msalAngularConfig: MsalAngularConfiguration;
}

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
