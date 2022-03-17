![ProptechOS logo](../../images/ProptechOS-logotype-ex.png)
# Available Connectors
The connectors below use the ProptechOS edge interface and translate the data of the underlying edge system to RealEstateCore Edge Message standard.

* **Partner level** What level of partnership that is behind the connector.
  * Gold: The partner has developed and maintains the connector
  * Silver: The partner and Idun share ownership and responsibility for the connector IP
  * Bronze: Idun developed and maintains the connector with help from the partner
* **Availability** Whether the connector is *Planned*, *In Progress* or *Available*
* **Observation** If the connector sends observations *from* the edge system (gathers telemetry)
* **Actuation** If the connector sends actuation *to* the edge system (handles commands)
* **Self-Provisioning** If the connector automatically creates devices in ProptechOS to represent the edge system, reducing the need for manual onboarding in order to use the connector.
* **Hosting** How the connector is deployed
  * *cloud* hosted (and connect to the edge system over the internet)
  * *Partner hosted* (and its connection to the edge system is managed by the partner)
  * *local VM* hosted (and connect to the edge system over the local network)
  * *local hardware* hosted (and connect to the edge system directly, or over unrouted local network)

### BMS SCADA
| Name          | Description                                                                    | Partner level  | Availability    | Observation, Actuation | Self-Provisioning | Hosting |
| ------------- | ------------------------------------------------------------------------------ | ------ | --------- | ----------- | ------------ | ------------ |
| ERTEK         | Partner connector for KNX PLCs via [ERTEK's](https://www.ertek.se/) product. | Gold   | Available |     :heavy_check_mark:, :heavy_minus_sign:     |     :heavy_minus_sign:     | KNX |
| Iconics       | Connector for [Iconics](https://iconics.com/). | Bronze  | Avaiable  |  :heavy_check_mark:, :heavy_minus_sign:     |     :heavy_minus_sign:     | Cloud / Local VM |
| Larmia        | Partner connector for interfacing with [Larmia](https://larmia.se/) Control Building Management System. | Gold   | Available   |     :heavy_check_mark:, :heavy_check_mark:   |     :heavy_check_mark:     | Partner hosted |
| Lindinvent    | Connector for [Lindinvent](https://www.lindinvent.se/) BMS.  |  Bronze  | Available  | :heavy_check_mark:, :heavy_check_mark:  |:heavy_minus_sign: | Local VM |
| Kabona        | Communicate with Kabona SCADA API. Using server URL and auth credentials. | Bronze | Available   |     :heavy_check_mark:, :heavy_check_mark:    |     :heavy_minus_sign:     | Local VM |
| KeyLogic      | Partner connector for sensor telemetry from [KeyLogic](https://www.keylogic.se/) SCADA                        | Gold   | Available   |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | Partner hosted |
| Modbus TCP/IP | Connector for Modbus devices. Uses Modbus client from modbus-master-tcp java library. | Bronze | Available   |     :heavy_check_mark:, :heavy_check_mark:    |     :heavy_minus_sign:     | Local Hardware / Local VM |
| Modbus RTU    | Connector for Modbus devices.  | Bronze  | Available  | :heavy_check_mark:, :heavy_minus_sign:  |:heavy_minus_sign: | Local Hardware  |
| Nordomatic    | Partner connector for [Nordomatic Styrportalen](https://nordomatic.com/se/styrportalen/). | Gold   | Available |     :heavy_check_mark:, :heavy_check_mark:     |     :heavy_minus_sign:     | Partner hosted |
| Nuuka    | Partner connector for [Nuuka products](https://www.nuuka.com/solutions). | Gold   | Available |     :heavy_check_mark:, :heavy_minus_sign:     |     :heavy_minus_sign:     | Partner hosted |
| OPC UA        | Connector for OPC UA Servers. Using server URL and auth credentials. | Bronze | Available   |     :heavy_check_mark:, :heavy_check_mark:    |     :heavy_minus_sign:     | Local VM |
| Siemens Desigo | Connector for [Siemens Desigo CC](https://new.siemens.com/global/en/products/buildings.html) for sensors and actuators. | Bronze | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | Local VM |
| Schneider Electric     | Mqtt client which is connected to Schneider [Ecostruxure via Smart Connector](https://github.com/BuildingsLabs/EboIoTEdgeConnector) and an MQTT broker. | Silver | Available   |     :heavy_check_mark:, :heavy_check_mark:    |     :heavy_minus_sign:     | Local VM |
| Schneider Electric     | Partner conenctor using the native feature MQTT of [EcoStruxure v4.0](https://ecostruxure.schneider-electric.com/) and later.  |  Gold  | Available  | :heavy_check_mark:, :heavy_check_mark:  |:heavy_minus_sign: | Partner hosted  |
| Web Port      | Communicate with [Wep Port](https://webport.se/) API. Using server URL and auth credentials.  | Bronze | Available   |     :heavy_check_mark:, :heavy_check_mark:    |     :heavy_minus_sign:     | Cloud |

### Wireless and IoT Sensors
| Name          | Description                                                                    | Partner level  | Availability    | Observation, Actuation | Self-Provisioning | Hosting |
| ------------- | ------------------------------------------------------------------------------ | ------ | --------- | ----------- | ------------ | ------------ |
| Axians        | Conenctor to the general IoT data of [Axians](https://www.axians.se/)  | Gold | In Progress  | :heavy_check_mark:, :heavy_minus_sign:  |:heavy_check_mark: |  Partner hosted  |
| Disruptive Technologies  | Connector for [Disruptive](https://www.disruptive-technologies.com/) API Server Sent Events, SSE, for all devices in config file (api limit is 50 active stream connections per user or service-account) Authenticated via generated JWT  | Bronze | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_check_mark:     | Cloud |
| Flowity       | Partner connector for camera and ML-based presence analysis with AFRY [Flowity](https://www.flowity.io/).    | Gold   | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | Partner hosted |
| Freesi        | Connector to the [Freesi](https://iisy.fi/?lang=en) REST API, establishes sessions using server URL and cookie. | Bronze | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | Cloud |
| Haltian       | Connector to the [Haltian](https://haltian.com/) system mqtt broker.                  | Bronze | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | Partner hosted |
| Helvar        | Connector to the [Helvar](https://helvar.com/) API, establishes sessions using server URL and api key. | Gold | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_check_mark:     | Cloud |
| Integral (obsolete)      | Tranlates IDs and format. | Bronze | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | Cloud |
| Integral   | Connector for Integrals API.  | Bronze  | In progress | :heavy_check_mark:, :heavy_minus_sign:  |:heavy_check_mark: | Cloud |
| Lummelunda    | Partner connector for [Lummelunda](https://lummelunda.tech/) IoT devices. | Gold   | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_check_mark:     | Partner hosted |
| Lummelunda (obsolete) | Connector for interacting with [Lummelunda](https://lummelunda.tech/) Web API. Http client based, establishes sessions using server URL and auth credentials  | Bronze | Available   |     :heavy_check_mark:, :heavy_minus_sign:     |     :heavy_check_mark:     | Partner hosted |
| Talkpool      | Partner connector for [Talkpool](https://talkpool.com/) IoT devices. | Gold   | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | Partner hosted |
| Treon         | Conenctor translating IDs and format to RealEstateCore Edge Message. | Bronze | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | Cloud |
| UbiqiSense    | Connector for [UbiqiSense](https://www.ubiqisense.com/) API, camera and ML based presence analysis. Establishes sessions using server URL and access token. | Bronze | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | Cloud |

### Metering, Media and Utility
| Name          | Description                                                                    | Partner level  | Availability    | Observation, Actuation | Self-Provisioning | Hosting |
| ------------- | ------------------------------------------------------------------------------ | ------ | --------- | ----------- | ------------ | ------------ |
| Elvaco   | Connector for [Elvaco metering products](https://www.elvaco.se/en/) | Bronze  |  Available | :heavy_check_mark:, :heavy_minus_sign: |:heavy_minus_sign: | Cloud |
| EMU           | Connector for [EMU Professional](https://www.emu-metering.de/) TCP/IP devices. Http client based            | Bronze | Available   |     :heavy_check_mark:, :heavy_minus_sign:     |     :heavy_minus_sign:     | Cloud |
| eSys          | Partner connector for meters (m-bus) via [eSys product ePort](http://eport.esysab.se/). | Gold   | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_check_mark:     | Partner hosted |
| Fortum        | Connector for [Fortum](https://www.fortum.se/foretag) energy data.  | Bronze  | In Progress  | :heavy_check_mark:, :heavy_minus_sign:  |:heavy_minus_sign: | Cloud |
| Greenview     | Connector for [Greenview](https://greencon.se/greenview/)  | Gold  | Available  | :heavy_check_mark:, :heavy_minus_sign:  |:heavy_check_mark: | Partner hosted |
| Mestro        | Connector for [Mestro](https://mestro.com/en/) API(1.2.0). Using server URL and auth credentials.    | Bronze | Available |     :heavy_check_mark:, :heavy_minus_sign:     |     :heavy_minus_sign:     | Cloud |
| Metry         | Connector for [Metry](https://metry.io/) API. Using server URL and access token.  | Bronze | Available   |     :heavy_check_mark:, :heavy_minus_sign:     |     :heavy_minus_sign:     | Cloud |



### Other
| Name          | Description                                                                    | Partner level  | Availability    | Observation, Actuation | Self-Provisioning | Hosting |
| ------------- | ------------------------------------------------------------------------------ | ------ | --------- | ----------- | ------------ | ------------ |
| Cisco         | Camera and ML based presence analysis.                                         | Bronze | In progress |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_minus_sign:     | - |
| Crestron      | Partner connector for getting telemetry out of [JML](https://jml.se/) Crestron processor.            | Gold   | Available |     :heavy_check_mark:, :heavy_minus_sign:      |     :heavy_check_mark:     | Partner hosted |
| CTEK        | Partmner connector for [CTEK](https://www.ctek.com/) EV chargers   | Bronze  | In Progress  | :heavy_check_mark:, :heavy_check_mark:  |:heavy_minus_sign: | Cloud |
| EpSpot        | Connector for [EpSpot](https://www.epspot.com/) EV chargers   | Bronze  | Available  | :heavy_check_mark:, :heavy_check_mark:  |:heavy_minus_sign: | Cloud |
| Swegon        | Connector for [Swegon](https://www.swegon.com/) HVAC equipment and presence data.  | Gold | Available   |     :heavy_check_mark:, :heavy_minus_sign:     |     :heavy_check_mark:     | Cloud |
| SMHI          | Connector for [SMHI API](https://opendata.smhi.se/apidocs/). Supported device types are FORECAST and ANALYSIS. Http client based  | Bronze | Available   |     :heavy_check_mark:, :heavy_minus_sign:     |     :heavy_minus_sign:     | Cloud |
