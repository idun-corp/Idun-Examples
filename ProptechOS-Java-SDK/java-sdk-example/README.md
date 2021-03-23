# Application description:
The intention of this application is to demonstrate the most common usecases of ProptechOS Java SDK.
By default the application calls all main sdk services to retrieve the data from ProptechOS API.
In order to start streaming api client spring component `StreamingApiServiceClient` should be injected in main class in the following way:
````java
@Bean
	public CommandLineRunner commandLineRunner(StreamingApiServiceClient client) {
		return args -> {
			client.subscribeOnObservations();
		};
	}
```` 

# Application Set-up
## System requirements:
* JDK 11
* Maven


### Fix 'application.properties' config file:
* `BASE_API_URL` - base ProptechOS api url "https://test.proptechos.com/api"
* `APP_CLIENT_ID` - your application id in ProptechOS (also the application ID registered in Azure Active Directory)
* `APP_CLIENT_SECRET` - your application secret in ProptechOS (also the secret generated for your Azure Active Directory application)

If streaming api is needed then not mandatory streaming api properties should be uncommented and updated correspondingly:
* `EVENTHUB_NAME` - Azure kafka enebled eventhub name
* `EVENTHUB_NAMESPACE` - Azure eventhub namespace
* `EVENTHUB_SHARED_ACCESS_KEY` - Azure eventhub namespace shared access key to connect to



### Build and run Application:
* Navigate to <APPLICATION_HOME> and run
```
bash
mvn clean install
```

* Then start the application
```
bash
cd target/
java -jar java-sdk-example-1.0-SNAPSHOT.jar
```
