
# Application Set-up
## System requirements:
* JDK 11
* Maven


### Fix 'application.properties' config file:
* Rename file 'application.properties.copy' -> 'application.properties'
* `azure.aad.clientId`
  should be set to registered application client id
* `azure.aad.clientSecret`
  should be set to registered application client secret
* `proptechos.api.base.url`  
  should be set to base ProptechOS url (e.x. 'https://proptechos.com/api')
* `proptechos.api.application.scope`
  should be set to ProptechOS application scope


### Build and run Application:
* Navigate to <APPLICATION_HOME> and run
```
bash
mvn clean install
```

* Then start the application
Application requires two arguments 
1. Axiom path (e.x. '/asset')
2. Axiom id (e.x. 'b40a3596-199d-4ff2-b8ee-73fd47a564d5')
```
bash
cd target/
java -jar jsonld-parser-0.0.1-SNAPSHOT.jar /asset b40a3596-199d-4ff2-b8ee-73fd47a564d5
```
