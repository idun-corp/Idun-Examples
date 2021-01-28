![ProptechOS logo](../images/ProptechOS-logotype-ex.png)

# Edge Interface
ProptechOS uses [RealEstateCore Edge messages](https://github.com/RealEstateCore/rec/tree/master/api/edge_messages) and is implemented using [Microsoft Azure IoT Hub](https://azure.microsoft.com/en-us/services/iot-hub/)

## Edge Messages according to RealEstateCore
ProptechOS use the RealEstateCore Edge message format for messages to and from edge Devices, like Sensors and Actuators. Use the [documentation from RealEstateCore](https://github.com/RealEstateCore/rec/tree/master/api/edge_messages) to get more familiar with how the edge messages can be used.

## Get started - Edge development Kit
To get going you will need get the following:
* **User access to ProptechOS**  
You can create your test Devices and fetch their Device Key, either via the API or the user interface.  
[ProptechOS user interface](https://proptechos.com/ui)
* **The ProptechOS Edge URL**  
It is `idun-iothub-02-prod.azure-devices.net`  
There you go.

With the URL, Device ID and Device Key, you can construct your edge connection string: `HostName=<<ProptechOS edge url;DeviceId=<<Device ID>>;SharedAccessKey=<<Device Key>>`  
Idun can also help you get going by [setting up access](dev-kit-via-1password)

## Sample Connector
Here is a full working ProptechOS edge connector, [with code and a step-by-step guide](examples).

## Implemented and Available Connectors
Perhaps you don't need to build a new connector. Here is a [list of all Idun certified connectors](List-of-Available-Connectors).
