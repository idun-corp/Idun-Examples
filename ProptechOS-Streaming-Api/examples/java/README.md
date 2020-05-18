
## Application Set-up
 ### System requirements:
 * JDK 11
 * Maven
 ### Fix 'application.properties' config file:
 * _'event.hub.name'_ - EventHub name (kafka topic);
 * _'spring.kafka.bootstrap-servers'_ property should be set to _'eventhub entity path:9093'_  
 * _'spring.kafka.properties.security.protocol'_ property should be set to _'SASL_SSL'_  
 * _'spring.kafka.properties.sasl.mechanism'_ property should be set to _'PLAIN'_    
 * _'spring.kafka.consumer.group-id'_ property should be set to _'$Default'_ (literally)  
 * _'spring.kafka.properties.sasl.jaas.config'_ property should be set to the string _'org.apache.kafka.common.security.plain.PlainLoginModule required username="$ConnectionString" password="\<the connection string\>";'_ (Should be copied as it is and updated correctly)
 ### Build and run Application:
 * Navigate to <APPLICATION_HOME> and run
 ``` bash
 mvn clean install 
 ```
 * Then start the application
 ```bash
 cd target/
 java -jar streaming-api-0.0.1-SNAPSHOT.jar
 ```
 
