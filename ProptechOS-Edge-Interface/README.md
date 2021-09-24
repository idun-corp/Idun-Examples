![ProptechOS logo](../images/ProptechOS-logotype-ex.png)

# Edge Interface
ProptechOS uses [RealEstateCore Edge messages](https://github.com/RealEstateCore/rec/tree/master/api/edge_messages) and is implemented using [Microsoft Azure IoT Hub](https://azure.microsoft.com/en-us/services/iot-hub/). The piece of software that connects Edge Devices with ProtpechOS edge interface is called an *Edge Connector*.

## Edge Messages
ProptechOS follow the **RealEstateCore Edge message format** for messages to and from edge Devices, Sensors and Actuators.  
Read the *[documentation from RealEstateCore](https://github.com/RealEstateCore/rec/tree/master/api/edge_messages)* to get familiar with edge messages and how they are used and see the [json schema specifying their format](https://github.com/RealEstateCore/rec/tree/master/api/edge_messages/edge_message.schema.json).

## ProptechOS Edge Connector overview
Here is a [summary that goes through the logic of a ProptechOS Edge Connectors](Edge-Connector-Overview).

## Sample Connector
Here is a full working ProptechOS edge connector, [with working code and a step-by-step guide](examples).

## Implemented and Available Connectors
Perhaps you don't need to build a new connector. Here is a [list of all Idun certified connectors](List-of-Available-Connectors).

## Get started
To get going you will need get the following:
* **User access to ProptechOS**  
You can create your test Devices either via the API or the user interface, and fetch the Device keys currently only via API.  
[ProptechOS user interface](https://proptechos.com/ui)
* **(Optionally) Application access to ProptechOS**
With an application access you can fetch Device keys via API programmatically.
* **The ProptechOS Edge URL**  
For trial or professional use of ProptechOS it is `idun-multi-iothub-01.azure-devices.net`
For dedicated cloud instances of ProptechOS the Edge URL will be shared in 1Password.
There you go.

With the URL, Device ID and Device Key, you can construct your edge connection string: `HostName=<<ProptechOS edge url;DeviceId=<<Device ID>>;SharedAccessKey=<<Device Key>>`  
Idun can also help you get going by [setting up access](dev-kit-via-1password)
