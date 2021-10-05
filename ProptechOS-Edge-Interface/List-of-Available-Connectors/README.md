## Implemented ProptechOS Edge Connectors
The connectors below consume data from underlying systems, transforms received data to RealEstateCore edge messages,
and sends them to ProptechOS. Some connectors also handle Actuations, so that ProtpechOS can control the underlying system (see the Actuation column). The Connector receives messages from ProptechOS and transforms it to s message that the underlying system can process(Actuations). See availability in table

| Name          | Description                                                                    | Partner level  | Status    | Observation, Actuation | Self-Provisioning | Type |
| ------------- | ------------------------------------------------------------------------------ | ------ | --------- | ----------- | ------------ | ------------ |
| EMU           | Connector for [EMU Professional](https://www.emu-metering.de/) TCP/IP devices. Http client based            | Bronze | Available   |     Yes, No     |      No      | Cloud |
| Elvaco   | Connector for [Elvaco metering products](https://www.elvaco.se/en/) | Bronze  |  Available | Yes, No | No  | Cloud |
| Kabona        | Communicate with Kabona SCADA API. Using server URL and auth credentials. | Bronze | Available   |     Yes, Yes    |      No      | Local VM |
| Mestro        | Connector for [Mestro](https://mestro.com/en/) API(1.2.0). Using server URL and auth credentials.    | Bronze | Available |     Yes, No     |      No      | Cloud |
| Metry         | Connector for [Metry](https://metry.io/) API. Using server URL and access token.  | Bronze | Available   |     Yes, No     |      No      | Cloud |
| Modbus        | Bastec or general Modbus devices. Uses Modbus client from modbus-master-tcp java library. | Bronze | Available   |     Yes, Yes    |      No      | Local Hardware / Local VM |
| OPC UA        | Connector for OPC UA Servers. Using server URL and auth credentials. | Bronze | Available   |     Yes, Yes    |      No      | Local VM |
| Schneider     | Mqtt client which is connected to Schneider [Ecostruxure via Smart Connector](https://github.com/BuildingsLabs/EboIoTEdgeConnector) system mqtt broker. | Bronze | Available   |     Yes, Yes    |      No      | Local VM |
| Schneider     | Partner conenctor using the native feature MQTT of [EcoStruxure v4.0](https://ecostruxure.schneider-electric.com/) and later.  |  Gold  | Available  | Yes, Yes  | No  | Partner hosted  |
| SMHI          | Connector for [SMHI API](https://opendata.smhi.se/apidocs/). Supported device types are FORECAST and ANALYSIS. Http client based  | Bronze | Available   |     Yes, No     |      No      | Cloud |
| Web Port      | Communicate with [Wep Port](https://webport.se/) API. Using server URL and auth credentials.  | Bronze | Available   |     Yes, Yes    |      No      | Cloud |
| Larmia        | Partner connector for interfacing with [Larmia](https://larmia.se/) Control Building Management System. | Gold   | Available   |     Yes, Yes   |      No      | Partner hosted |
| Lindinvent    | Connector for [Lindinvent](https://www.lindinvent.se/) BMS.  |  Bronze  | Available  | Yes, Yes  | No  | Local VM |
| Nordomatic    | Partner connector for [Nordomatic Styrportalen](https://nordomatic.com/se/styrportalen/). | Gold   | Available |     Yes, Yes     |      No      | Partner hosted |
| ERTEK         | Partner connector for KNX PLCs via [ERTEK's](https://www.ertek.se/) product. | Gold   | Available |     Yes, Yes     |      No      | KNX |
| eSys          | Partner connector for meters (m-bus) via [eSys product ePort](http://eport.esysab.se/). | Gold   | Available |     Yes, No      |      No      | Partner hosted |
| Flowity       | Partner connector for camera and ML-based presence analysis with AFRY Flowity.    | Gold   | Available |     Yes, No      |      No      | Partner hosted |
| Talkpool      | Partner connector for [Talkpool](https://talkpool.com/) IoT devices. | Gold   | Available |     Yes, No      |      No      | Partner hosted |
| Lummelunda    | Partner connector for [Lummelunda](https://lummelunda.tech/) IoT devices. | Gold   | Available |     Yes, No      |      No      | Partner hosted |
| Lummelunda (obsolete) | Connector for interacting with [Lummelunda](https://lummelunda.tech/) Web API. Http client based, establishes sessions using server URL and auth credentials  | Bronze | Available   |     Yes, No     |      No      | Partner hosted |
| Disruptive Technologies  | Connector for [Disruptive](https://www.disruptive-technologies.com/) API Server Sent Events, SSE, for all devices in config file (api limit is 50 active stream connections per user or service-account) Authenticated via generated JWT  | Bronze | Available |     Yes, No      |      No      | Cloud |
| Helvar        | Connector to the [Helvar](https://helvar.com/) API, establishes sessions using server URL and api key. | Bronze | Available |     Yes, No      |      No      | Cloud |
| Freesi        | Connector to the [Freesi](https://iisy.fi/?lang=en) REST API, establishes sessions using server URL and cookie. | Bronze | Available |     Yes, No      |      No      | Cloud |
| Treon         | Tranlates IDs and format. | Bronze | Available |     Yes, No      |      No      | Azure EventHub |
| Integral (obsolete)      | Tranlates IDs and format. | Bronze | Available |     Yes, No      |      No      | Azure EventHub |
| Integral   | Connector for Integrals API.  | Bronze  | In progress | Yes, No  | Yes  | Cloud |
| Haltian       | Connector to the [Haltian](https://haltian.com/) system mqtt broker.                  | Bronze | Available |     Yes, No      |      No      | Partner hosted |
| UbiqiSense    | Connector for [UbiqiSense](https://www.ubiqisense.com/) API, camera and ML based presence analysis. Establishes sessions using server URL and access token. | Bronze | Available |     Yes, No      |      No      | Cloud |
| Cisco         | Camera and ML based presence analysis.                                         | Bronze | In progress |     Yes, No      |      No      | - |
| Crestron      | Partner connector for getting telemetry out of [JML](https://jml.se/) Crestron processor.            | Gold   | Available |     Yes, No      |      No      | Partner hosted |
| KeyLogic      | Partner connector for sensor telemetry from [KeyLogic](https://www.keylogic.se/) SCADA                        | Gold   | Available   |     Yes, No      |      No      | Partner hosted |
| Siemens Desigo | Connector for [Siemens Desigo CC](https://new.siemens.com/global/en/products/buildings.html) for sensors and actuators. | Bronze | Available |     Yes, No      |      No      | HTTP TCP/IP Local |
| EpSpot  | Connector for EpSpot EV chargers   | Bronze  | In Progress  | Yes, Yes  | Yes  | Cloud |
| Fortum  | Connector for Fortum energy data.  | Bronze  | In Progress  | Yes, No  | No  | Cloud |
| Axians   | Conenctor to the general IoT data of Axians  | Gold | Available  | Yes, No  | Yes  |  Partner hosted  |
