![ProptechOS logo](images/ProptechOS-logotype-ex.png)

# Edge Connector Module Development

## Introduction
The intention of this guide is to help a developer to get started developing an Edge Connector for ProptechOS. An RealEstateCore Edge Connector sends and receives RealEstateCore edge messages to a RealEstateCore system. Observations are sent from the Edge system and ActuationCommands are sent from the RealEstateCore system and received by the edge system. To get more familiar with RealEstateCore edge messages, see the [Edge message specification at the RealEstateCore Github repository](https://github.com/RealEstateCore/rec/tree/master/api/edge_messages).

ProptechOS is built using the Microsoft suite of underlying IoT technologies. Hence, a RealEstateCore edge connector for ProptechOS sends RealEstateCore edge messages using a client compatible with the Microsoft IoT Hub. The easiest way to get started is to use the Microsoft IoT Hub Client, which is what this guide describes.

As added illustrations for the guide, we have built a simple example Edge Connector, ProptechOS-REC-connector (see [the Edge Interface example folder](../exmamples)). Have a look and compare the example code with the description in this guide.

## Edge Connector development

The Development guide is based on Microsoft’s IoT quickstart documentations:  
[Quickstart: Send telemetry to an Azure IoT hub and read it with a Java application](https://docs.microsoft.com/en-us/azure/iot-hub/quickstart-send-telemetry-java)  
[Quickstart: Send cloud-to-device messages with IoT Hub](https://docs.microsoft.com/en-us/azure/iot-hub/iot-hub-java-java-c2d)  
Use those guides and other Microsoft documentation to find additional details about protocols, clients, gateways and details on a lower level.

### Edge Connector implementation

To implement connector we need to cover a couple of basic things:
* Parse configuration
In order to use json configuration throughout the project we need to parse a json file to java object. We will use the configuration values to simulate a device with a valid connection string and sensors (as RealEstateCore subdevices).
* Generate (simulated) telemetry
In this step we will create a generator class that will simulate messages from our device and sensors.
S* end the telemetry messages
To simulate a real device we need to periodically send messages. We will implement this behaviour in scope of this step.
* Implement message callback
When we create actuation via ProptechOS we need something to receive those actuation messages. The callback implemented in this section will be responsible for that.
* Generate (simulated) device actuation response
This is similar to generation of telemetry from the device, but here we simulate a response from the device, so that ProptechOS understands if the device received the ActuationCommand or not.
* Implement main() method that runs the DeviceClient.
main() method is the starting point for our connector application. In scope of this method, we will use all the classes described above.

#### Prerequisite

* Have access to a ProptechOS instance
* Have Device created and  registered in ProptechOS and have both Device Id and Key
* Have Sensors created in ProptechOS (the Sensor should be a subDevice of the Device above)
* Have Actuators created in ProptechOS (the Actuator should be a subDevice of the Device above)
* Java SE Development Kit 8 or higher
* Apache Maven 3
* Port 8883 must be open in your firewall. We will use MQTT protocol, which communicates over port 8883. This port may be blocked in some corporate and educational network environments.
