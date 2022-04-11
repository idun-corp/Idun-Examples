# .NET Core B2C Client Credential grant flow example
This is a .NET Core Console App example that illustrates Client Credential grant flow in .NET.

Open the [Program.cs](https://github.com/idun-corp/Idun-Examples/blob/apps-team-b2c-examples/ProptechOS-Api/examples/netcore-b2c-client-credential-flow-console-app/Program.cs) file and and fill the properties:
* CLIENT_ID = with your application client id
* CLIENT_SECRET = with your application secret
* BASE_URL = 'https://{your_environment}.proptechos.com/api/json'
* SCOPE_NAME = 'https://proptechos.onmicrosoft.com/(application)/.default'

```C#
var clientId = "<CLIENT_ID>";
var clientSecret = "<CLIENT_SECRET>";
var scopeName = "<SCOPE_NAME>";
var baseUrl = "<BASE_URL>";
```

## Run example
Hit Ctrl + F5 and look at the console. The API call to PropechOS is executed after an access token is aquaired
