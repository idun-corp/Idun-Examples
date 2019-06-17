# Consuming Idun Streaming API
Idun Streaming API is delivered via Azure EventHub, and can be consumed via a Kafka Consumer or via the Azure EventHub specific EventProcessorHost client.
See https://cwiki.apache.org/confluence/display/KAFKA/Clients and https://kafka.apache.org/quickstart for general information on Kafka and Kafka consumers, or the Quickstart tutorials at https://github.com/Azure/azure-event-hubs-for-kafka/ for the specifics of consuming Azure Eventhub via the Kafka protocol. The Microsoft docs resource at https://docs.microsoft.com/sv-se/azure/event-hubs/event-hubs-for-kafka-ecosystem-overview will give an overview of translating Eventhub concepts to Kafka concepts.
In summary:
  the kafka _'bootstrap.servers'_ property should be set to _'eventhub entity path:9093'_
  the kafka _'ssl.ca'_ property should be set to _'SASL_SSL'_
  the kafka _'sasl.username'_ property should be set to _'$ConnectionString'_ (literally)
  the kafka _'sasl.password'_ property should be set to _'<the connection string>'_

For information about event hubs and how to consume messages via the EventProcessorHost, please refer to https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-programming-guide

 ## The Idun message

     {
       "observation": {
         "value": "21.5",
         "quantityKind": "Temperature",
         "sensorId": "0234c884-f8dc-48d6-b627-2f0d8f8705d6",
         "observationTime": "2019-06-06T13:33:32.379Z"
       },
       "sensor": "https://example.graph.idunrealestate.com/api/sensor/0234c884-f8dc-48d6-b627-2f0d8f8705d6"
     }

 The Idun Streaming message contains a json formatted RealEstateCore (https://www.realestatecore.io) Observation and the URI of the sensor that produced the observation. To get additional information on the message and the sensor, follow the sensor URI (e.g. the device, the BuildingStrcutureComponent, the RealEstate or related Actuators).
