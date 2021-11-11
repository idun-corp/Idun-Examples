# Consuming ProptechOS Streaming API with Dedicated Processor

## Prerequisites

* EventHub Connection String in format
  `Endpoint=sb://<eventhub-namespace>.servicebus.windows.net/;SharedAccessKeyName=<access-key-name>;SharedAccessKey=<access-key>`
* EventHub Name
* Consumer Group name
* Blob Storage Connection String in format
  `DefaultEndpointsProtocol=https;AccountName=<storage-account-name>;AccountKey=<storage-account-key>;EndpointSuffix=core.windows.net`
* Blob Container Name

## How to Run

1. Update `appsettings.json` file and set variables:
   * EventHubConnectionString
   * EventHubName
   * EventHubConsumerGroup (if empty then '$Default' ConsumerGroup will be used)
   * BlobStorageConnectionString
   * BlobContainerName
2. Please ensure you have blob container created
3. Build & Run
