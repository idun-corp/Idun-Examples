
function testAuth(baseUrl){
   const ss = SpreadsheetApp.getActiveSpreadsheet();
   var responseCode = 0;
   try {
     responseCode = UrlFetchApp.fetch(baseUrl + '/realestate?' + getQueryString({}), getAuth()).getResponseCode();
   }
   catch(err) {
     responseCode = 400;
 }

 if (responseCode == 200){
   ss.toast('Authenticated: ' + (responseCode == 200));
 } else {
   if (useOAuth2()) {
     ss.toast('The Authorization token might have expired, please click "Sign in" button on sidebar.');
   }
   else {
     ss.toast('The Authorization token might have expired, insert a new one in the Auth sheet.');
   }
 }
 return responseCode == 200;
}


function onOpen() {
  SpreadsheetApp.getUi()
  .createMenu('ProptechOS')
  .addItem('Authorize Sidebar', 'showAzureAuthorizeSidebar')
  // .addItem('Fetch Data Summary', 'dataSummary')
  // .addItem('Fetch Sensor Details', 'sensorDetails')
  // .addItem('Fetch Sensors per Building', 'sensorsLifeSign')
  .addToUi();
}
//
// * * * * *
// BaseURL & Auth token & PageSize from spreadsheet
// * * * * *
function useOAuth2() {
  const ss = SpreadsheetApp.getActiveSpreadsheet();
  let value = !!ss.getRangeByName('use_oauth2').getValue();
  return value;
}
function getBaseUrl(){
  const ss = SpreadsheetApp.getActiveSpreadsheet();
  return ss.getRangeByName('base_url').getValue();
}
function getAuth(){
  const ss = SpreadsheetApp.getActiveSpreadsheet();

  if (useOAuth2()) {
    var service = getAzureService();

    if (service.hasAccess()) {
      var bearerToken = 'Bearer ' + service.getAccessToken();
    }
  }
  else {
    var bearerToken = ss.getRangeByName('auth_token').getValue();
  }

  o = {};
  o.headers = {
    'Authorization': bearerToken,
    'Content-Type': 'application/json'
  };

  return o;
}
function getPageSize(){
  const ss = SpreadsheetApp.getActiveSpreadsheet();
  return ss.getRangeByName('page_size').getValue();
}
const baseUrl = getBaseUrl();
//const options = getAuth();
const size = getPageSize();


function getQueryString(params){
  if (!('page' in params)){
    params.page = 0;
  }
  if (!('size' in params)){
    params.size = 10;
  }
  return Object.keys(params).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(params[key])).join('&');
}

function getApiJson(url){
  const apiResponse = UrlFetchApp.fetch(url,getAuth());
  const json = apiResponse.getContentText();
  return JSON.parse(json)
}

function getApiRealEstate(id){
  return getApiJson(baseUrl + '/json/realestate/' + id);
}

function getApiRealEstates(params){
  return getApiJson(baseUrl + '/json/realestate?' + getQueryString(params));
}

function getApiBuildings(params){
  return getApiJson(baseUrl + '/json/realestatecomponent?' + getQueryString(params));
}

function getApiBuilding(id){
  return getApiJson(baseUrl + '/json/realestatecomponent/' + id);
}

function getApiBuildingComponents(params){
  return getApiJson(baseUrl + '/json/buildingcomponent?' + getQueryString(params));
}

function getApiBuildingComponent(id){
  return getApiJson(baseUrl + '/json/buildingcomponent/' + id);
}

function getApiActuator(id, params){
  return getApiJson(baseUrl + '/json/actuator/' + id + '?' + getQueryString(params));
}

function getApiActuators(params){
  return getApiJson(baseUrl + '/json/actuator?' + getQueryString(params));
}

function getApiSensor(id, params){
  return getApiJson(baseUrl + '/json/sensor/' + id + '?' + getQueryString(params));
}

function getApiSensors(params){
  return getApiJson(baseUrl + '/json/sensor?' + getQueryString(params));
}

function getApiSensorObservations (id, params){
  return getApiJson(baseUrl + '/sensor/' + id +'/observation?' + getQueryString(params));
}

function getApiLatestObservation(id){
  return getApiJson(baseUrl + '/sensor/' + id +'/observation/latest');
}
