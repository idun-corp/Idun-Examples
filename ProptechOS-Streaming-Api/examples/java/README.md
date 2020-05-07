# Consuming the Streaming API
ProptechOS Streaming API is delivered via Azure EventHub, and can be consumed via a Kafka Consumer or via the Azure EventHub specific EventProcessorHost client.  
See https://cwiki.apache.org/confluence/display/KAFKA/Clients and https://kafka.apache.org/quickstart for general information on Kafka and Kafka consumers, or the Quickstart tutorials at https://github.com/Azure/azure-event-hubs-for-kafka/ for the specifics of consuming Azure Eventhub via the Kafka protocol. The Microsoft docs resource at https://docs.microsoft.com/sv-se/azure/event-hubs/event-hubs-for-kafka-ecosystem-overview will give an overview of translating Eventhub concepts to Kafka concepts. 

For information about event hubs and how to consume messages via the EventProcessorHost, please refer to https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-programming-guide

 ## The Idun ProptechOS Stream message:
```json
 {
   "observation": {
     "value": "21.5",
     "quantityKind": "Temperature",
     "sensorId": "0234c884-f8dc-48d6-b627-2f0d8f8705d6",
     "observationTime": "2019-06-06T13:37:32.379Z"
   },
   "sensor": "https://example.graph.idunrealestate.com/api/sensor/0234c884-f8dc-48d6-b627-2f0d8f8705d6"
 }
```

 The Idun Streaming message contains a json formatted RealEstateCore (https://www.realestatecore.io) Observation and the URI of the sensor that produced the observation. To get additional information on the message and the sensor, follow the sensor URI (e.g. the device, the BuildingComponent, the RealEstate or related Actuators).
 
 ## Application Set-up:
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
 