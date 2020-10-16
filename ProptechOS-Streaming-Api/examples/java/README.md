
# Application Set-up
## System requirements:
* JDK 11
* Maven


### Fix 'application.properties' config file:
* `event.hub.name`
  should be set to EventHub name (`EVENTHUB_NAME`) (corresponds to kafka topic)
* `spring.kafka.bootstrap-servers`
  should be set to _`<EVENTHUB_NAMESPACE>.servicebus.windows.net:9093`_ 
* `spring.kafka.properties.sasl.jaas.config`  
  should be set to the string  
  _`org.apache.kafka.common.security.plain.PlainLoginModule required username="$ConnectionString" password="<EVENTHUB_CONNECTION_STRING>"`_  
  (note that the username is litterally "€ConnectionString" while the password is replaced by the connection string you have received from Idun)


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
java -jar streaming-api-0.0.1-SNAPSHOT.jar
```
