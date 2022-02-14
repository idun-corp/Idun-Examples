# Angular B2C implicit OAuth2 flow example

This is a simple application that just illustrates how to configure authentication and authorization in the ProptechOS API using [Microsoft Authentication Library for Angular](https://www.npmjs.com/package/@azure/msal-angular).

## Configuration

Open [environment.ts](https://github.com/idun-corp/Idun-Examples/blob/apps-team-b2c-examples/ProptechOS-Api/examples/angular-b2c-implicit-oauth-flow/src/environments/environment.ts) file and fill the properties:

* _AUTHORITY_HREF_ = `https://proptechos.b2clogin.com/proptechos.onmicrosoft.com/b2c_1a_signin/`
* _BASE_URL_ = ProptechOS api base url (e.g. `https://proptechos.com/api`)
* _CLIENT_ID_ = your application client id
* _SCOPE_NAME_ = `https://proptechos.onmicrosoft.com/{myenvironment}/api.use`

```javascript
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
```

### Check out configurations for the MsalModule in the [app.module.ts](https://github.com/idun-corp/Idun-Examples/blob/apps-team-b2c-examples/ProptechOS-Api/examples/angular-b2c-implicit-oauth-flow/src/app/app.module.ts).

```javascript
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MsalModule.forRoot(
      new PublicClientApplication({
        auth: environment.msalConfig.auth,
        cache: {
          cacheLocation: BrowserCacheLocation.LocalStorage,
          storeAuthStateInCookie: false,
        }
      }),
      {
        interactionType: InteractionType.Redirect,
        authRequest: { scopes: environment.msalConfig.consentScopes }
      },
      {
        interactionType: InteractionType.Redirect,
        protectedResourceMap: new Map(environment.msalConfig.protectedResourceMap)
      }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true
    },
    HttpClient,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
```


### Check out configuration in the [app-routing.module.ts](https://github.com/idun-corp/Idun-Examples/blob/apps-team-b2c-examples/ProptechOS-Api/examples/angular-b2c-implicit-oauth-flow/src/app/app-routing.module.ts)

```javascript
const routes: Routes = [{
  path: "",
  canActivate: [MsalGuard],
  component: AppComponent,
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```

### API call to ProptechOS is executed in the [app.component.ts](https://github.com/idun-corp/Idun-Examples/blob/apps-team-b2c-examples/ProptechOS-Api/examples/angular-b2c-implicit-oauth-flow/src/app/app.component.ts)

```javascript
public ngOnInit() {
    this.msalService.handleRedirectObservable()
      .subscribe(() => {
        this.http.get<any>(`${environment.proptechOsApiUrl}/person/me`, {
          headers: {
            "Content-Type": "application/json",
            "X-Requested-With": "XMLHttpRequest"
          },
          observe: "response",
          responseType: "json"
        })
        .subscribe(response => this.response = response.body)
      })
  }
```

## Run example

* PORT = with a port specified in redirect uri. Ex. `http://localhost:4200/`.

Run `ng serve --port <PORT>` for a dev server. Navigate to `http://localhost:<PORT>/`. The app will automatically reload if you change any of the source files.
