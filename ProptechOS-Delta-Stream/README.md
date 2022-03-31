# Delta Stream
The intention of Delta Stream is to notify subscribers about each change in Digital Twin graph of ProptechOS. ProptechOS Delta Stream is delivered via Azure EventHub, and can be consumed via a Kafka Consumer .
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

## The Idun ProptechOS Delta Stream message:

```json
{
"iris" : ["https://proptechos.com/api/sensor/0234c884-f8dc-48d6-b627-2f0d8f8705d6"],
"operation": "CREATE"
}
```
### Where:
* **iris** - the array of affected digital twin URIs;
* **operation** - the operation that was executed (possible values: _'CREATE'_, _'UPDATE'_, _'DELETE'_)

## Proprietary Microsoft Eventhub client
For information about event hubs and how to consume messages via the EventProcessorHost, please refer to https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-programming-guide

# Twin history
Each change in Digital Twin graph of ProptechOS is stored into Azure Blobs and could be retrieved via ProptechOS API _/history_ endpoints.
Thus, Delta Stream notifies that change has been introduced and Twin history reflects what actually has been changed.

## List of history endpoints:
* GET /api/json/realestate/{id}/history
* GET /api/json/realestatecomponent/{id}/history
* GET /api/json/buildingcomponent/{id}/history
* GET /api/json/room/{id}/history
* GET /api/json/storey/{id}/history
* GET /api/json/asset/{id}/history
* GET /api/json/device/{id}/history
* GET /api/json/sensor/{id}/history
* GET /api/json/actuator/{id}/history
* GET /api/json/actuationinterface/{id}/history

Each endpoint accepts a mandatory path parameter `id` - twin id and two optional parameters `startTime` and `endTime`.

## History endpoint response example

```json
[
  {
    "snapshotTime": "2022-03-24T21:26:14.219Z",
    "operation": "[CREATE|UPDATE|DELETE]",
    "agent": "test@company.com",
    "snapshot": {
      "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "hasAlias": [
        {
          "id": "string",
          "isMemberOfAliasNamespace": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
        }
      ],
      "hasGeoReferenceOrigo": "0.0;0.0;0.0",
      "littera": "string",
      "popularName": "string",
      "class": "RealEstate",
      "ownedBy": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    }

  }
]
```

