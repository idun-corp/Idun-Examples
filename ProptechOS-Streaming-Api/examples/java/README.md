
# Application Set-up
## System requirements:
* JDK 11
* Maven


### Fix 'application.properties' config file:
* `'event.hub.name'`
  should be set to EventHub name (in the connection string, this is referred to as _EntityPath_) (corresponds to kafka topic);
* `'spring.kafka.bootstrap-servers'`
  property should be set to _'eventhub entity path:9093'_  
  (in the connection string this is referred to as Endpoint and prefixed by _sb://_ which should be excluded from the bootstrap server property)
* `'spring.kafka.properties.sasl.jaas.config'`  
  property should be set to the string  
  _'org.apache.kafka.common.security.plain.PlainLoginModule required username="$ConnectionString" password="\<the connection string\>";'_  
  (Should be copied as it is and updated correctly)


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
