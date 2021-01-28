## Implemented ProptechOS Edge Connectors
The connectors below consume data from underlying systems, transforms received data to RealEstateCore edge messages,
and sends them to ProptechOS. Some connectors also handle Actuations, so that ProtpechOS can control the underlying system (see the Actuation column). The Connector receives messages from ProptechOS and transforms it to s message that the underlying system can process(Actuations). See availability in table

| Module name   | Description                                                                    | Type   | Status      | Observations | Actuations |
| ------------- | ------------------------------------------------------------------------------ | ------ | ----------- | ------------ | ---------- |
| EMU           | Interacting with EMU Professional TCP/IP devices. Http client based            | Bronze | Available   |     Yes      |     No     |
| Lummelunda | Module for interacting with Lummelunda Web API. Http client based, establishes sessions using server URL and auth credentials  | Bronze | Available   |     Yes      |     No     |
| Kabona        | Communicate with Kabona SCADA API. Http client based, establishes sessions using server URL and auth credentials. | Bronze | Available   |     Yes      |     Yes    |
| LoRaWAN       | Interacting with LoRaWAN Event hub. Subscribes to Azure Event Hub messages.    | Bronze | Available   |     Yes      |     No     |
| Mestro        | Interacting with Mestro API(1.2.0). Http client based, establishes sessions using server URL and auth credentials.    | Bronze | Available |     No       |     No     |
| Metry         | Interacting with Metry API. Http client based, establishes sessions using server URL and access token.  | Bronze | Available   |     Yes      |     No     |
| Modbus        | Interacting with Bastec devices by using Modbus TCP. Uses Modbus client from modbus-master-tcp java library. | Bronze | Available   |     Yes      |     Yes    |
| OPC UA        | Interacting with OPC UA Servers. Http client based, establishes sessions using server URL and auth credentials. | Bronze | Available   |     Yes      |     Yes    |
| Schneider     | Mqtt client which is connected to Schneider system mqtt broker.                | Bronze | Available   |     Yes      |     Yes    |
| SMHI          | Interacting with SMHI API. Supported device types are FORECAST and ANALYSIS. Http client based  | Bronze | Available   |     Yes      |     No     |
| Web Port      | Communicate with Wep Port API. Http client based, establishes sessions using server URL and auth credentials.  | Bronze | Available   |     Yes      |     Yes    |
| Larmia        | Partner module for interfacing with Larmia Control Building Management System. | Gold   | Available   |     Yes      |      Yes   |
| Nordomatic    | Partner module for Nordomatic Styrportalen.                                    | Gold   | In progress |     Yes      |    Yes     |
| ERTEK         | Partner module for PLCs via ERTEK's product.                                   | Gold   | In progress |     Yes      |    Yes     |
| eSys          | Partner module for meters (m-bus) via eSys product ePort.                      | Gold   | Available |     Yes      |    No      |
| Flowity       | Partner module for camera and ML-based presence analysis with AFRY Flowity.    | Gold   | Available |     Yes      |    No      |
| Talkpool      | Partner module for Talkpool IoT devices.                                       | Gold   | Available |     Yes      |    No      |
| Disruptive Technologies  | Generates JWT access token and subscribes to disruptive API Server Sent Events, SSE, for all devices in config file(api limit is 50 active :stream connections per user or service-account)  | Bronze | Available |     Yes      |    No      |
| Helvar        | Interacting with Helvar API. Http client based, establishes sessions using server URL and api key. | Bronze | Available |     Yes      |    No      |
| Freesi        | Interacting with Freesi REST API. Http client based, establishes sessions using server URL and cookie provided by API. | Bronze | Available |     Yes      |    No      |
| Treon         | Interacting with YIT Event hub. Subscribes to Azure Event Hub messages using kafka client | Bronze | Available |     Yes      |    No      |
| Integral      | Interacting with YIT Event hub. Subscribes to Azure Event Hub messages using kafka client | Bronze | Available |     Yes      |    No      |
| Haltian       | Mqtt client which is connected to Haltian system mqtt broker.                  | Bronze | Available |     Yes      |    No      |
| UbiqiSense    | Camera and ML based presence analysis. Interacting with UbiqiSense API. Http client based, establishes sessions using server URL and access token. | Bronze | Available |     Yes      |    No      |
| Cisco         | Camera and ML based presence analysis.                                         | Bronze | In progress |     Yes      |    No      |
| Crestron      | Partner module for getting telemetry out of JML Crestron processor.            | Gold   | In progress |     Yes      | No         |
