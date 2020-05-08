![ProptechOS logo](../../images/ProptechOS-logotype-ex.png)

# Edge Connector Module Development

# Introduction
The intention of this guide is to help a developer to get started developing an Edge Connector for ProptechOS. An RealEstateCore Edge Connector sends and receives RealEstateCore edge messages to a RealEstateCore system. Observations are sent from the Edge system and ActuationCommands are sent from the RealEstateCore system and received by the edge system. To get more familiar with RealEstateCore edge messages, see the [Edge message specification at the RealEstateCore Github repository](https://github.com/RealEstateCore/rec/tree/master/api/edge_messages).

ProptechOS is built using the Microsoft suite of underlying IoT technologies. Hence, a RealEstateCore edge connector for ProptechOS sends RealEstateCore edge messages using a client compatible with the Microsoft IoT Hub. The easiest way to get started is to use the Microsoft IoT Hub Client, which is what this guide describes.

As added illustrations for the guide, we have built a simple example Edge Connector, ProptechOS-REC-connector (see [the Edge Interface example folder](../exmamples)). Have a look and compare the example code with the description in this guide.

# Edge Connector development

The Development guide is based on Microsoft’s IoT quickstart documentations:  
[Quickstart: Send telemetry to an Azure IoT hub and read it with a Java application](https://docs.microsoft.com/en-us/azure/iot-hub/quickstart-send-telemetry-java)  
[Quickstart: Send cloud-to-device messages with IoT Hub](https://docs.microsoft.com/en-us/azure/iot-hub/iot-hub-java-java-c2d)  
Use those guides and other Microsoft documentation to find additional details about protocols, clients, gateways and details on a lower level.

## Edge Connector implementation

To implement connector we need to cover a couple of basic things:
* **Parse configuration**  
In order to use json configuration throughout the project we need to parse a json file to java object. We will use the configuration values to simulate a device with a valid connection string and sensors (as RealEstateCore subdevices).
* **Generate (simulated) telemetry**  
In this step we will create a generator class that will simulate messages from our device and sensors.
* **Send the telemetry messages**  
To simulate a real device we need to periodically send messages. We will implement this behaviour in scope of this step.
* **Implement message callback**  
When we create actuation via ProptechOS we need something to receive those actuation messages. The callback implemented in this section will be responsible for that.
* **Generate (simulated) device actuation response**  
This is similar to generation of telemetry from the device, but here we simulate a response from the device, so that ProptechOS understands if the device received the ActuationCommand or not.
* **Implement main() method that runs the DeviceClient.**  
main() method is the starting point for our connector application. In scope of this method, we will use all the classes described above.

## Prerequisite

* Have access to a ProptechOS instance
* Have Device created and  registered in ProptechOS and have both Device Id and Key
* Have Sensors created in ProptechOS (the Sensor should be a subDevice of the Device above)
* Have Actuators created in ProptechOS (the Actuator should be a subDevice of the Device above)
* Java SE Development Kit 8 or higher
* Apache Maven 3
* Port 8883 must be open in your firewall. We will use MQTT protocol, which communicates over port 8883. This port may be blocked in some corporate and educational network environments.

### Parse configuration
1. Add a json configuration file to the resources folder. Using this format:

```json
{
	“iotHubAdress”: "youriothub.azure-devices.net",
	“deviceId”: "device_id_from_ProptechOS",
	“deviceKey”: "device_key_from_ProptechOS",
	“sensors”: [
		{
			“quantityKind”: "Temperature",
			“sensorId”: "sensor_id_from_ProptechOS"
		}
	],
	“actuators”: [
		{
			“actuatorId”: "actuator_id_from_ProptechOS"
		}
	]
}
```
2. Create a java class that will contain all the values from configuration. Add method to created class:
`public String createConnectionString();`  
this method should return a connection string to the device which is registered in the IoT hub. Connection string format is next:
`“HostName=iotHubAdress;DeviceId=deviceId;SharedAccessKey=deviceKey”`  
We will need this connection string to create DeviceClient [Azure Docs - DeviceClient class](https://docs.microsoft.com/en-us/java/api/com.microsoft.azure.sdk.iot.device.deviceclient?view=azure-java-stable)

### Generate (simulated) telemetry
1. Our telemetry will be RecMessage class with fields:

```java
private final String format = "rec3.2";
private String deviceId;
private List<RecObservation> observations;
private List<RecActuationCommand> actuationCommands;
private List<RecActuationResponse> actuationResponses;
private ModuleMessage edgeState;
```
where `RecObservation` is class with fields:

```java
private final ZonedDateTime observationTime;
private final Object value;
private final String quantityKind;
private final String sensorId;
```

This `RecMessage` is the actual content that will be sent to ProptechOS.  
We will talk about `RecActuationCommand` and `RecActuationResponse` in more detail when we move to receiving messages from the ProptechOS section.  
`ModuleMessage` object will not be used in our example.

2. Create new class TelemetryGenerator and implement next methods

```java
public RecMessage generateTelemetry();
private double generateRandomTemperature();
private Sensor getRandomSensor();
```

`generateTelemetry()` should create a `RecMessage` with randomly generated temperature for sensorId which is randomly chosen from config file.

### Send the telemetry messages
Create a new java class. Let this class implement a Runnable interface, since we want to send our messages in a separate thread, in order not to block the parent thread with infinite loop. This interface will require an overridden `run()` method.

Implementation of run() method:
* Use `generateTelemetry()` from `TelemetryGenerator`
* Parse received  `RecMessage` to a JSON string or to a byte array. Pass parsed `RecMessage` as parameter of  `Message` object constructor. [Azure docs - Message Class](https://docs.microsoft.com/en-us/java/api/com.microsoft.azure.sdk.iot.device.message?view=azure-java-stable)  
* send created Message using DeviceClient method
sendEventAsync(Message message, IotHubEventCallback callback, Object callbackContext)
 * message Message - the message to be sent.
 * callback IotHubEventCallback - the callback to be invoked when a response is received. Can be null.
 * callbackContext Object - a context to be passed to the callback. Can be null
* add Thread.sleep(sendPeriod) in order to send messages with the frequency every sendPeriod that you will specify

### Implement message callback, to receive messages from ProptechOS
The real flow of actuation, that we are about to repeat with our simple connector,  is next:
Actuation created in ProptechOS
Edge connector receives RecMessage with  RecActuationCommand
Edge connector creates new RecMessage with RecActuationResponse  and sends it to ProptechOS
Temperature values of device should be changed to one from the actuation command.
