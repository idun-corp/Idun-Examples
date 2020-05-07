![ProptechOS logo](../images/ProptechOS-logotype-ex.png)

# Edge Interface

## Edge Messages
ProptechOS use the RealEstateCore Edge message format for messages to and from edge Devices, like Sensors and Actuators. Use the [documentation from RealEstateCore](https://github.com/RealEstateCore/rec/tree/master/api/edge_messages) to get more familiar with how the edge messages can be used.

## Edge Connector
Review the Developer Guide [RealEstateCore Edge Connector for ProptechOS using Microsoft IoT Hub Client](edge-connector-module) to get guided through the java example.

## Get started
To get going you will need the following information at minimum:
- IoT Hub Hostname (`iotHubAddress` in the examples)
- Device ID (`deviceId` in examples)
- Device Key (`deviceKey` in examples)
- Sensor ID (`sensorId` in examples)
- Sensor QuantityKind (`QuantityKind` in examples)
- Actuator ID (`actuatorId` in examples)

When starting the ProptechOS Edge Connector development, Idun will set up acces, and a set of test devices. The device key and other secrets will be shared using [1Password](https://1password.com/) in a vault entry like below. Idun will invite you to 1Password. Accept the invitation, and after Idun has confimred your account and set up a shared vault, you can access your test device information.
![Test Devices Vault](images/ProptechOS-Edge-Interface/1Pass_Test_Devices.png)
