# Consuming ProptechOS Streaming API with Azure Functions

## Prerequisites

* EventHub Connection String in format
  `Endpoint=sb://<eventhub-namespace>.servicebus.windows.net/;SharedAccessKeyName=<access-key-name>;SharedAccessKey=<access-key>;EntityPath=<eventhub-name>`
* Consumer Group name (optional)

## How to Run

1. Update `local.settings.json` file and set variables:
   * EventHubConnectionString
   * EventHubConsumerGroup (if empty then '$Default' ConsumerGroup will be used)
2. Build & Run
