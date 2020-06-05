
// using https://github.com/gsuitedevs/apps-script-oauth2 as LIbrary resource
// Instructions to Add library:
// 1. Click on the menu item "Resources > Libraries..."
// 2. In the "Find a Library" text box, enter the script ID 1B7FSrk5Zi6L1rSxxTDgDEUsPzlukDsi4KGuTMorsTQHhGBzBkMun4iDF and click the "Select" button.
// 3. Choose a version in the dropdown box (usually best to pick the latest version).
// 4. Click the "Save" button.

// Instructions to register app
// 1. redirect URI: https://script.google.com/macros/d/<< your script ID >>/usercallback
// 2. Add "API permission" for ProptechOS API, scope .Api.

function getAzureService() {
  var TENANT_ID = 'd4218456-670f-42ad-9f6a-885ae15b6645';
  var CLIENT_ID = '<< add your client id >>';
  var CLIENT_SECRET = '<< add your secret >>';
  var ACCESS_SCOPE = 'https://<< foo >>.proptechos.com/api/Api.Use';


  return OAuth2.createService('Azure')
      .setAuthorizationBaseUrl('https://login.microsoftonline.com/' + TENANT_ID + '/oauth2/v2.0/authorize')
      .setTokenUrl('https://login.microsoftonline.com/' + TENANT_ID + '/oauth2/v2.0/token')
      .setClientId(CLIENT_ID)
      .setClientSecret(CLIENT_SECRET)
      .setCallbackFunction('authCallback')
      .setPropertyStore(PropertiesService.getUserProperties())
      .setScope(ACCESS_SCOPE);
}

function authCallback(request) {
  var template = HtmlService.createTemplateFromFile('OAuth2_Callback');
  template.email = Session.getEffectiveUser().getEmail();
  template.isSignedIn = false;
  template.error = null;

  var title;

  try {
    var service = getAzureService();
    var authorized = service.handleCallback(request);
    template.isSignedIn = authorized;
    title = authorized ? 'Access Granted' : 'Access Denied';
  }
  catch (e) {
    template.error = e;
    title = 'Access Error';
  }

  template.title = title;

  return template
    .evaluate()
    .setTitle(title)
    .setSandboxMode(HtmlService.SandboxMode.IFRAME);
}

function getUser() {
  var service = getAzureService();

  if (!service.hasAccess()) {
    return null;
  }

  var base64 = service.getAccessToken().split('.')[1].replace(/-/g, '+').replace(/_/g, '/');
  var jsonPayload = Utilities.newBlob(Utilities.base64Decode(base64, Utilities.Charset.UTF_8)).getDataAsString();

  var context = JSON.parse(jsonPayload);

  return {
    displayName: context.name,
    token: service.getAccessToken()
  };
}

function signOut() {
  var service = getAzureService();

  if (service.hasAccess()) {
    getAzureService().reset();

    return true;
  }

  return false;
}

function include(filename) {
  return HtmlService.createHtmlOutputFromFile(filename).getContent();
}

function showAzureAuthorizeSidebar() {
  if (useOAuth2()) {
    var service = getAzureService();

    var template = HtmlService.createTemplateFromFile('OAuth2_Sidebar');

    template.email = Session.getEffectiveUser().getEmail();
    template.isSignedIn = service.hasAccess();
    template.authorizationUrl = service.getAuthorizationUrl();

    var page = template.evaluate()
    .setTitle('Azure: sign in')
    .setSandboxMode(HtmlService.SandboxMode.IFRAME);

    SpreadsheetApp.getUi().showSidebar(page);
  }
}
