
﻿# Consuming Idun Streaming API using EventProcessorHost
**(Deprecated since ProptechOS 0.0.4)** 
This page will describe how to use this example of how to consume messages from Idun Streaming API.
For further information about event hubs and how to consume messages, please refer to https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-programming-guide

## Prerequisites
This example is based on C# and .NET CORE using Visual Studio 2017.
Before you begin make sure you have got your event hub name and event hub connection string from your Idun representative.
You will also need an Azure Storage Account and it's Account Name, Account Key and the name of the container to use.
For information on setting up an Azure Storage Account, follow the section "Create an Azure Storage Accoint" from https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-dotnet-standard-getstarted-receive-eph#create-an-azure-storage-account

## Configuration
In the project root folder, you will find the configuration file 'appsettings.json'. You need to edit this file and add your credentials and configuration parameters.
The appsettings.json contains of five configuratrion parameters.
 - EhEntityPath : Enter the event hub name here
 - EhConnectionString : Enter the event hub connection string here
 - StorageContainerName : Enter the name of the storage container to use for the event processing host.
 - StorageAccountName : Enter the Account name of the storage account here.
 - StorageAccountKey: Enter the Account key of the storage account here.

 ## What this example does
 Once configuration is done, build the project and make sure that the nuget packages are restored.
 Start Program.cs to begin consuming Idun messages. This example application simply just print the messages to the console.
 This is happending in the ProcessEventsAsync method of the SimpleEventProcessor class.
 The SimpleEventProcessor class is registered to the EventProcessorHost which in turn manages the instances of SimpleEventProessor instances to use depending on available partitions.
 This means that several SimpleEventProcess instances will run in parallell, something to consider when you move on from this example and want to call your application.
 For deeper knowledge on the EventProcessorHost, refer to it's documentation at https://docs.microsoft.com/en-us/dotnet/api/microsoft.servicebus.messaging.eventprocessorhost?view=azure-dotnet

 ## The Idun message
 What you will see in the console is all messages that is published to your event hub starting at your time of program startup.
 The data is a json formatted object and based on Real Estate Core (https://www.realestatecore.io)

PLEASE NOTE: The camel case of json parameter names is in this preview inconsistent, future implementations will produce camel case as isPartOfDevice instead of current ispartofdevice etc.

 The json object contains six nested objects:

  ### Observation
  Contains information about an observed sensor value.

      "observation": {
        "observationTime": "2018-06-18T09:01:21.934Z",
        "value": 22.9,
        "quantityKind": "Temperature",
        "sensorId": "b00df055-1f34-46d3-be02-22b1c56f892b"
      }

- observationTime : This is the time when the observation was made. It using the ISO-8601 format as specified in RFC3339 (https://www.ietf.org/rfc/rfc3339.txt)
- value : This is the value of the observation.
- quantityKind : Specifies the kind of the observation value. In this case it is a value from a temperature sensor.
- sensorId : Refers to the unique identifier of the sensor who made the observation.

### Sensor
Contains information about the sensor that made the observation.

    "sensor": {
      "id": "b00df055-1f34-46d3-be02-22b1c56f892b",
      "class": "Sensor",
      "ispartofdevice": "d794def5-4282-4a6d-882a-2362518cc5f2",
      "quantitykind": "Temperature",
      "locatedin": "2956c8ea-b12f-4c30-be0f-b1b89662cc57",
      "powersource": "CommunicationBus"
    }

- id : The unique identifier of the sensor.
- ispartofdevice : The unique idenfier of the device that the sensor is part of.
- quantityKind : Specifies the kind of the observation values that the sensor produce.
- locatedin : Refers to the buildingstructurecomponent of where this sensor is installed.

### Device
Contains information about the device that the sensor is part of.

    "device": {
      "id": "d794def5-4282-4a6d-882a-2362518cc5f2",
      "class": "Device",
      "devicefunction": "DataAqusition,Control",
      "servedby": "69aa19f7-3e44-4d71-b831-fa9e5584e1c7,db0003f5-3c25-4a81-a698-0776d69bd853",
      "littera": "LB01/02-96:Office-TD21:35",
      "locatedin": "2956c8ea-b12f-4c30-be0f-b1b89662cc57",
      "powersource": "CommunicationBus"
    }

- id : The unique identifier of the device.
- locatedin : Refers to the buildingstructurecomponent of where this sensor is installed.

### Building Structure Component
Contains information about the physical space where the device is installed, like OfficeRoom or an Atrium etc. Refer to Real Estate Core for more details.

    "buildingstructurecomponent": {
      "id": "2956c8ea-b12f-4c30-be0f-b1b89662cc57",
      "class": "OfficeRoom",
      "locatedin": "bd3a9840-4067-4b34-baec-42fe7a57a958",
      "locatedatlocalcoordinates": "-;-;35.21",
      "littera": "96:Office"
    }

- id : The unique identifier of the building structure component.
- class : Defines what kind of building structure component this is, e.g. OfficeRoom.
- locatedin : Refers to the buildingstructure this building structure component belong to / is part of.

### Building Structure
Contains information about the building. Refer to Real Estate Core for more details.

    "buildingstructure": {
      "id": "bd3a9840-4067-4b34-baec-42fe7a57a958",
      "class": "BuildingStructure",
      "aliasid": 591009,
      "littera": 591009,
      "popularname": ""Main Building",
      "locatedat": "57,12349;17,5439;10.0",
      "belongsto": "6855aeaf-3c5e-4376-afdd-9c046ae76b05"
  }

- id : The unique identifier of the building structure.
- locatedat : The longitude / latitude of the position of the building.

### Real Estate
Contains information about the real estate the building belong to. Refer to Real Estate Core for more details.

    "realestate": {
      "id": "6855aeaf-3c5e-4376-afdd-9c046ae76b05",
      "class": "RealEstate",
      "aliasid": 591,
      "littera": 591,
      "locatedat": "57,1234;17,543;10.0",
      "popularname": "Main Building"
    }

- id : The unique identifier of the real estate.
- locatedat : The longitude / latitude of the position of the building.

### Format
Specifies the version of Real Estate Core used, e.g. rec2.2

	"format": "rec2.2"


### Example of a complete message
Example of a complete Idun message.

	{
	  "observation": {
		"observationTime": "2018-06-18T09:01:21.934Z",
		"value": 22.9,
		"quantityKind": "Temperature",
		"sensorId": "b00df055-1f34-46d3-be02-22b1c56f892b"
	  },
	  "sensor": {
		"id": "b00df055-1f34-46d3-be02-22b1c56f892b",
		"class": "Sensor",
		"ispartofdevice": "d794def5-4282-4a6d-882a-2362518cc5f2",
		"quantitykind": "Temperature",
		"locatedin": "2956c8ea-b12f-4c30-be0f-b1b89662cc57",
		"powersource": "CommunicationBus"
	  },
	  "device": {
		"id": "d794def5-4282-4a6d-882a-2362518cc5f2",
		"class": "Device",
		"devicefunction": "DataAqusition,Control",
		"servedby": "69aa19f7-3e44-4d71-b831-fa9e5584e1c7,db0003f5-3c25-4a81-a698-0776d69bd853",
		"littera": "LB01/02-96:Office-TD21:35",
		"locatedin": "2956c8ea-b12f-4c30-be0f-b1b89662cc57",
		"powersource": "CommunicationBus"
	  },
	  "buildingstructurecomponent": {
		"id": "2956c8ea-b12f-4c30-be0f-b1b89662cc57",
		"class": "OfficeRoom",
		"locatedin": "bd3a9840-4067-4b34-baec-42fe7a57a958",
		"locatedatlocalcoordinates": "-;-;35.21",
		"littera": "96:Office"
	  },
	  "buildingstructure": {
		"id": "bd3a9840-4067-4b34-baec-42fe7a57a958",
		"class": "BuildingStructure",
		"aliasid": 591009,
		"littera": 591009,
		"popularname": ""Main Building",
		"locatedat": "57,12349;17,5439;10.0",
		"belongsto": "6855aeaf-3c5e-4376-afdd-9c046ae76b05"
	  },
	  "realestate": {
		"id": "6855aeaf-3c5e-4376-afdd-9c046ae76b05",
		"class": "RealEstate",
		"aliasid": 591,
		"littera": 591,
		"locatedat": "57,1234;17,543;10.0",
		"popularname": "Main Building"
	  },

	  "format": "rec2.2"
	}
