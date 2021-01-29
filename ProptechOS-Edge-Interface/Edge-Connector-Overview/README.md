![ProptechOS logo](../../images/ProptechOS-logotype-ex.png)

# Edge Connector Overview
The job of the Edge Connector can be split up into:

0. Auth and connection
1. ID translation
2. Format translation
3. Logic and buffering (perhaps)
4. Exception handling and meta-telemetry (perhaps)
5. Self-provisioning (a little more advanced than "basics" but much cheaper to maintain over time)

## 0. Auth and connection
Since ProptechOS is built using Azure IoT technologies, connecting to the ProptechOS edge interface is really connection to [Azure IoT Hub](https://docs.microsoft.com/en-us/azure/iot-hub/). You can use the [IoT Hub SDK](https://github.com/Azure/azure-iot-sdks) and a Device Client or connect using a vanilla [MQTT client](https://mqtt.org/software/) or AMQP client. (Our [sample connector uses the Java Device Client](../examples)
ProptechOS relies on Device ID + Device Key for authentication and authorization. That means that even in cases where an edge connector handles many Devices (sometimes thousands), each Device is authorized separately (More on that under ID translation) instead of e.g. relying on a single certificate. On the other hand, all you need is the IoT Hub hostname and the Device ID and Key pair(s).

When developing your client, you can either test against ProptechOS, or if you need complete insight into logs and diagnostics you can set up your own Azure IoT Hub (for free). You can use our [Device connection checker tool](examples/ProptechOS-device-connection-checker) to troubleshoot.

When you have successfully connected, you can use our [IoT Hub Listener tool](examples/ProptechOS-iothub-listener) to see if your messages are successfully sent to IoT Hub (more on this under Format translation). If your messages are correct RealEstateCore format (see 2. Format translation, below), you can use ProptechOS straight away, and see your messages in the Stream or in your sensor's `/sensor/{id}/observation/latest` endpoint of the API.

## 1. ID translation
In ProptechOS (as in all RealEstateCore platforms) edge messages are sent by Devices on behalf of Sensors or Actuators ("sub-devices"). The ProptechOS Device ID and Device Key is used to authorize each message. And similarly, messages are sent from ProptechOS to Devices on behalf of their sub-devices (e.g. an Actuation Command to an Actuator).

The ProptechOS ID of the Device sending the message and the sub-device it refers to is needed. Hence, the Edge Connector must be able to translate the IDs from the edge system (e.g. the BMS tag, the LoRa Dev-EUI or BACnet Device ID) to the equivalent ProptechOS IDs.

The edge connector must keep a map between the edge system ID and the ProptechOS ID.

An alternative possibility is to rely on ProptechOS, and for the edge connector use the ProptehcOS API to look up the IDs. For instance, a BMS connector for the edge system `My BMS System` (sic) could query `proptechos.com/api/sensor?aliases=ns.proptechos.com/my-bms-system/my-tag-1-a` to get the ID of the ProptechOS sensor representing tag `my-tag-1-a` and its parent Device ID. (see more in the [Alias and Alias namespace section](../../ProptechOS-API/alias-alias-namespace))

## 2. Format translation
In ProptechOS (as in all RealEstateCore platforms) only messages with valid [RealEstateCore edge message format](https://github.com/RealEstateCore/rec/tree/master/api/edge_messages) are allowed. Hence, the Edge Connector must be able to translate the data from the edge system into the RealEstateCore format. See additional specification details at the `api/edge_messages`section of [the RealEstateCore docs](https://github.com/RealEstateCore/rec).

For brevity, ProptechOS uses UUIDs instead of the full IRI for Device, Sensor and Actuator ID, and QuantityKind label instead of the full IRI.

A Sensor message (from a Device to ProptechOS) with two observations can look like the following:
```json
{
  "format": "rec3.2",
  "deviceId": "ac0d1c27-01fc-471e-87a3-f8bb7275cd65",
  "observations": [
    {
      "observationTime": "2020-06-27T20:07:44Z",
      "value": 22.1,
      "quantityKind": "Temperature",
      "sensorId" : "6b858eb6-ed37-43af-80dd-c4f8a4744625"
    },
    {
      "observationTime": "2020-06-27T20:09:44Z",
      "value": 3,
      "quantityKind": "Presence",
      "sensorId" : "e8d3cea7-8a32-4a2b-a146-cb4f754be653"
    }
  ]
}
```
Note that multiple Sensors can make observations (at different times) that are sent together in the same message. Also note that it is the Device that sends the message, and that the intended schema is stated explicitly.

An Actuation message (from ProptechOS to a Device) can look like the following:
```json
{
  "format": "rec3.2",
  "deviceId": "9c664221-ed4d-4215-a2fa-6dedfa69ce00",
  "actuationCommands":[
    {
      "actuationCommandTime": "2020-06-27T20:11:44Z",
      "actuationCommandId": "26c1f1cc-a5fa-4ab5-babb-c680c4ffa3cc",
      "actuatorId": "d671fd55-eb32-48bb-a9b2-f003d735c0e8",
      "valueString": "20.0"
    }
  ]
}
```
to which the Actuator (via the Device) could respond (from Device to ProptechOS):
```json
{
  "format": "rec3.2",
  "deviceId": "9c664221-ed4d-4215-a2fa-6dedfa69ce00",
  "actuationResponses":[
    {
      "actuationCommandId": "26c1f1cc-a5fa-4ab5-babb-c680c4ffa3cc",
      "actuatorId": "d671fd55-eb32-48bb-a9b2-f003d735c0e8",
      "responseCode": "success",
      "actuationResponseTime": "2020-06-27T20:11:45Z"
    }
  ],
}
```

Edge messages are meant to be easy to consolidate, so that e.g. the ActuationResponse could be combined with Observations:
```json
{
  "format": "rec3.2",
  "deviceId": "9c664221-ed4d-4215-a2fa-6dedfa69ce00",
  "actuationResponses":[
    {
      "actuationCommandId": "26c1f1cc-a5fa-4ab5-babb-c680c4ffa3cc",
      "actuatorId": "d671fd55-eb32-48bb-a9b2-f003d735c0e8",
      "responseCode": "success",
      "actuationResponseTime": "2020-06-27T20:11:45Z"
    }
  ],
  "observations": [
    {
      "observationTime": "2020-06-27T20:10:44Z",
      "value": 22.1,
      "quantityKind": "Temperature",
      "sensorId" : "9df365d3-023f-4f7b-a51f-9bdadb18ffbd"
    },
    {
      "observationTime": "2020-06-27T20:10:54Z",
      "value": 3,
      "quantityKind": "Presence",
      "sensorId" : "32e23d7e-ab9f-47c2-ae2d-060d1f21ce41"
    }
  ]
}
```

ProptechOS supports all RealEstateCore edge message format versions since v2.3

## 3. Logic and buffering

Implementing Sensor observations for an edge connector is quite straight forward. Messages can just be forwarded to ProptechOS after ID and format translation. With Actuator actuations, there is a little bit of logic that also needs to be covered. See a sequence diagram of the full Actuation logic (including the origin and follow-up via the API) below.

![Actuation logic](../images/Actuation-Sequence-Diagram_-_RealEstateCore.png)



## 4. Exception handling and meta-telemetry

## 5. Self-provisioning
(a little more advanced than "basics" but much cheaper to maintain over time)