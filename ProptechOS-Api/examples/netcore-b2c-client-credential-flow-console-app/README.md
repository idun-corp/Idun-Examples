# .NET Core B2C Client Credential grant flow example
This example illustrates Client Credential grant flow in .NET

Open the [Program.cs](https://github.com/idun-corp/Idun-Examples/blob/apps-team-b2c-examples/ProptechOS-Api/examples/netcore-b2c-client-credential-flow-console-app/Program.cs) file and and fill the properties:
* CLIENT_ID = with your application client id
* CLIENT_SECRET = with your application secret
* PROPTECHOS_API_URL = 'https://(your_environment)proptechos.com/api/...'
* PROPTECHOS_APPLICATION_ID_URI = 'https://proptechos.onmicrosoft.com/(application)'

```C#
var clientId = "<CLIENT_ID>";
var clientSecret = "<CLIENT_SECRET>";
var proptechOsApiUrl = "<PROPTECHOS_API_URL>";
var proptechOsApplicationIdUri = "<PROPTECHOS_APPLICATION_ID_URI>";
```

## Run example
Hit Ctrl + F5 and look at the console. The API call to PropechOS is executed after an access token is aquaired
