# Streaming API
ProptechOS Streaming API is delivered via Azure EventHub, and can be consumed via a Kafka Consumer .  
## Kafka Client
See [Apache - Kafka clients](https://cwiki.apache.org/confluence/display/KAFKA/Clients) and [Apache - Kafka quickstart](https://kafka.apache.org/quickstart) for general information on Kafka and Kafka consumers.  
See the Quickstart tutorials at [Github - Azure eventhubs for kafka](https://github.com/Azure/azure-event-hubs-for-kafka/) for the specifics of consuming Azure Eventhub via the Kafka protocol.

The Microsoft docs resource at [event hubs for kafka ecosystem overview](https://docs.microsoft.com/sv-se/azure/event-hubs/event-hubs-for-kafka-ecosystem-overview) will give an overview of translating Eventhub concepts to Kafka concepts.

In summary:
* kafka topic translates to EventHub
* the **kafka _'bootstrap.servers'_** translates to **EventHub namespace** path (e.g. sb://idun-myproptechos-eventhubs-streamingapi.servicebus.windows.net/)
* the **kafka _'ssl.ca'_** property should be set to **_'SASL_SSL'_** 
* the **kafka _'sasl.username'_** property should be set to **_'$ConnectionString'_** (literally)  
* the **kafka _'sasl.password'_** property should be set to **_'\<the connection string\>'_**

## Example
See a full working example: [Java Spring ProptechOS Streaming API consumer](examples/java).


## The Idun ProptechOS Stream message:

```json
 ...
 "observations":
[
  {
   "value": "21.5",
   "quantityKind": "Temperature",
   "sensorId": "0234c884-f8dc-48d6-b627-2f0d8f8705d6",
   "observationTime": "2019-06-06T13:37:32.379Z"
  }
]
```

The Idun Streaming message contains a json formatted RealEstateCore (https://www.realestatecore.io) Observation and the URI of the sensor that produced the observation. To get additional information on the message and the sensor, follow the sensor URI (e.g. the device, the BuildingStrcutureComponent, the RealEstate or related Actuators).


## Proprietary Microsoft Eventhub client
For information about event hubs and how to consume messages via the EventProcessorHost, please refer to https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-programming-guide
