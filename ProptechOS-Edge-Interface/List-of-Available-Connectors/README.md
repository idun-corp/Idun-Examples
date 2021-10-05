## Implemented ProptechOS Edge Connectors
The connectors below consume data from underlying systems, transforms received data to RealEstateCore edge messages,
and sends them to ProptechOS. Some connectors also handle Actuations, so that ProtpechOS can control the underlying system (see the Actuation column). The Connector receives messages from ProptechOS and transforms it to s message that the underlying system can process(Actuations). See availability in table

| Name          | Description                                                                    | Type   | Status    | Observation | Actuation | Self-Provisioning | south-bound Communication | 
| ------------- | ------------------------------------------------------------------------------ | ------ | --------- | ----------- | --------- | ------------ | ------------ |
| EMU           | Interacting with EMU Professional TCP/IP devices. Http client based            | Bronze | Available   |     Yes      |     No     |      No      | HTTP TCP/IP internet |
| Elvaco   |   | Bronze  |  Available | Yes  |  No | No  | HTTP TCP/IP internet |
| Kabona        | Communicate with Kabona SCADA API. Using server URL and auth credentials. | Bronze | Available   |     Yes      |     Yes    |      No      | HTTP TCP/IP local |
| Mestro        | Connector for Mestro API(1.2.0). Using server URL and auth credentials.    | Bronze | Available |     No       |     No     |      No      | HTTP TCP/IP internet |
| Metry         | Connector for Metry API. Using server URL and access token.  | Bronze | Available   |     Yes      |     No     |      No      | HTTP TCP/IP internet |
| Modbus        | Bastec or general Modbus devices. Uses Modbus client from modbus-master-tcp java library. | Bronze | Available   |     Yes      |     Yes    |      No      | Modbus TCP |
| OPC UA        | Connector for OPC UA Servers. Using server URL and auth credentials. | Bronze | Available   |     Yes      |     Yes    |      No      | HTTP TCP/IP local |
| Schneider     | Mqtt client which is connected to Schneider system mqtt broker.                | Bronze | Available   |     Yes      |     Yes    |      No      | - |
| Schnedier     | Partner conenctor using the native feature MQTT of EcoStruxure v4.0 and later.  |  Gold  | Available  | Yes  | Yes  | No  | -  |
| SMHI          | Connector for SMHI API. Supported device types are FORECAST and ANALYSIS. Http client based  | Bronze | Available   |     Yes      |     No     |      No      | HTTP TCP/IP internet |
| Web Port      | Communicate with Wep Port API. Using server URL and auth credentials.  | Bronze | Available   |     Yes      |     Yes    |      No      | HTTP TCP/IP internet |
| Larmia        | Partner connector for interfacing with Larmia Control Building Management System. | Gold   | Available   |     Yes      |      Yes   |      No      | - |
| Lindinvent    | Connector for Lindinvent BMS.  |  Bronze  | Available  | Yes  | Yes  | No  | HTTP TCP/IP Local |
| Nordomatic    | Partner connector for Nordomatic Styrportalen.                                    | Gold   | Available |     Yes      |    Yes     |      No      | - |
| ERTEK         | Partner connector for PLCs via ERTEK's product.                                   | Gold   | Available |     Yes      |    Yes     |      No      | - |
| eSys          | Partner connector for meters (m-bus) via eSys product ePort.                      | Gold   | Available |     Yes      |    No      |      No      | - |
| Flowity       | Partner connector for camera and ML-based presence analysis with AFRY Flowity.    | Gold   | Available |     Yes      |    No      |      No      | - |
| Talkpool      | Partner connector for Talkpool IoT devices.                                       | Gold   | Available |     Yes      |    No      |      No      | - |
| Lummelunda    | Partner connector for Lummelunda IoT devices.                                     | Gold   | Available |     Yes      |    No      |      No      | - |
| Lummelunda (obsolete) | Connector for interacting with Lummelunda Web API. Http client based, establishes sessions using server URL and auth credentials  | Bronze | Available   |     Yes      |     No     |      No      | - |
| Disruptive Technologies  | Subscribes to disruptive API Server Sent Events, SSE, for all devices in config file (api limit is 50 active stream connections per user or service-account) Authenticated via generated JWT  | Bronze | Available |     Yes      |    No      |      No      | HTTP TCP/IP internet |
| Helvar        | Connector to the Helvar API, establishes sessions using server URL and api key. | Bronze | Available |     Yes      |    No      |      No      | HTTP TCP/IP internet |
| Freesi        | Connector to the Freesi REST API, establishes sessions using server URL and cookie. | Bronze | Available |     Yes      |    No      |      No      | HTTP TCP/IP internet |
| Treon         | Tranlates IDs and format. | Bronze | Available |     Yes      |    No      |      No      | Azure EventHub |
| Integral      | Tranlates IDs and format. | Bronze | Available |     Yes      |    No      |      No      | Azure EventHub |
| Haltian       | Connector to the Haltian system mqtt broker.                  | Bronze | Available |     Yes      |    No      |      No      | MQTT |
| UbiqiSense    | Camera and ML based presence analysis. Interacting with UbiqiSense API. Establishes sessions using server URL and access token. | Bronze | Available |     Yes      |    No      |      No      | HTTP TCP/IP internet |
| Cisco         | Camera and ML based presence analysis.                                         | Bronze | In progress |     Yes      |    No      |      No      | - |
| Crestron      | Partner connector for getting telemetry out of JML Crestron processor.            | Gold   | In progress |     Yes      |    No      |      No      | - |
| KeyLogic      | Partner connector for sensor telemetry from KeyLogic SCADA                        | Gold   | Available   |     Yes      |    No      |      No      | - |
| Siemens Desigo | Module for sensors and actuators.                                             | Bronze | In progress |     Yes      |    No      |      No      | HTTP TCP/IP Local |
