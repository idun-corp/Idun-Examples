# ![ProptechOS logo](//images/ProptechOS-logotype.png) API

The resources and state of them are exposed via an endpoint each per top level class, with GET and POST methods for reading and creating/changing them. Please see Open API Specification (Swagger) docs for up-to-date details.

# Authentication

Authentication in ProptechOS uses OAuth 2.0 protocol.
It can be separated into two categories:
* interactive authentication (for applications accessing the API on behalf of user, like web apps and UIs)
* application (daemon) authentication, for applications working without user interaction.

See more in [the Authentication section](ProptechOS-Api/authentication)

# JSON and JSON-LD
asdfasdf

# Using Aliases and AliasNamespaces

## Namespaces

### Retrievable
URI based
### Non-Retrievable
Non URI based
## Use cases
### Find your object in ProptechOS via your Alias I
Let’s say you want to get observations from a sensor that you have represented in your system as `https://mysystem.com/sensor/63350079-9c76-4580-a3a2-97b586dae15a`, then you can get that sensor by querying based on Alias:

**Request - Get Sensor based on Alias**
```
// line breaks for clarity
GET https://foo.proptechos.com/api/sensor?
alias_ids=https%3A%2F%2Fmysystem.com%2Fsensor%2F63350079-9c76-4580-a3a2-97b586dae15a
&page=0
&size=1
```
<details>
<summary> Full response</summary>

```
{
  "@context": {
    "@vocab": "https://w3id.org/rec/device"
  },
  "@type": "Sensor",
  "comment": {},
  "createdTime": "2019-04-09T13:05:18.220Z",
  "deviceMeasurementUnit": "https://w3id.org/rec/device/CubicMeter",
  "devicePlacementContext": "https://w3id.org/rec/device/SupplyAir",
  "deviceQuantityKind": "https://w3id.org/rec/core/Flow",
  "hasAlias": [
    {
      "@context": {
        "@vocab": "http://proptechos.com/ontology/extension",
        "Alias": "http://proptechos.com/ontology/extension/Alias",
        "isMemberOfAliasNamespace": "http://proptechos.com/ontology/extension/isMemberOfAliasNamespace"
      },
      "@type": "Alias",
      "isMemberOfAliasNamespace": {
        "@id": "https://foo.proptechos.com/api/aliasnamespace/91fc9574-6dbf-4bd8-9508-6ce1edeb28b2",
        "@type": "@id"
      },
      "@id": "https://mysystem.com/sensor/63350079-9c76-4580-a3a2-97b586dae15a"
    }
  ],
  "hasDeviceFunctionType": null,
  "hasSuperDevice": {
    "@id": "https://foo.proptechos.com/api/device/59f0f33b-9edd-42b8-b4c5-44d401402e46",
    "@type": "@id"
  },
  "isMountedInBuildingComponent": {
    "@id": "https://foo.proptechos.com/api/buildingcomponent/c3d8823d-1a7f-49bd-96af-89522e3549a0",
    "@type": "@id"
  },
  "@id": "https://foo.proptechos.com/api/sensor/a755904b-7ba6-49db-9bc2-05053226af17",
  "littera": null,
  "observesActuator": {
    "@id": null,
    "@type": "@id"
  },
  "popularName": null,
  "servesBuilding": {
    "@id": null,
    "@type": "@id"
  },
  "servesBuildingComponent": {
    "@id": null,
    "@type": "@id"
  },
  "source": {
    "powerSource": "CommunicationBus"
  },
  "updatedTime": "2019-06-14T13:30:08.677Z"
}

```
</details>
The ProptechOS ID is in the response:

```
"@id": "https://foo.proptechos.com/api/sensor/a755904b-7ba6-49db-9bc2-05053226af17"
```

Then you can get observations using that ID  
**Request - Get Observations for Sensor**
```
GET https://foo.proptechos.com/api/sensor/a755904b-7ba6-49db-9bc2-05053226af17/observation
```
<details>
<summary> Full response</summary>

```
[
  {
    "observationTime": "2020-05-05T12:25:18.563Z",
    "value": 19
  },
  {
    "observationTime": "2020-05-05T12:25:35.799Z",
    "value": 19
  },
  {
    "observationTime": "2020-05-05T12:25:43.928Z",
    "value": 19
  },
  {
    "observationTime": "2020-05-05T12:26:17.196Z",
    "value": 19
  },
  {
    "observationTime": "2020-05-05T12:26:26.378Z",
    "value": 19
  },
  {
    "observationTime": "2020-05-05T12:27:36.215Z",
    "value": 19
  },
  {
    "observationTime": "2020-05-05T12:27:44.470Z",
    "value": 19
  },
  {
    "observationTime": "2020-05-05T12:28:43.718Z",
    "value": 19
  }
]
```
</details>

### Find your object in ProptechOS via your Alias II
Instead, let's say you want to find a presence sensor in a room, based only on your ID for the room `https://mysystem.com/room/40b9c6a8-e738-4da8-a2e1-e661e1977e7a`, then you first request the room, with that Alias, and then a presence sensor in that room.
**Request - Get Room by Alias**

```
// line breaks for clarity
GET https://foo.proptechos.com/api/sensor?
alias_ids=https%3A%2F%2Fmysystem.com%2Froom%2F40b9c6a8-e738-4da8-a2e1-e661e1977e7a
&page=0
&size=1
```
<details>
<summary> Full object</summary>

```
{
  "@context": {
    "@vocab": "https://w3id.org/rec/core",
    "Room": "https://w3id.org/rec/core/Room",
    "isPartOfBuilding": "https://w3id.org/rec/core/isPartOfBuilding",
    "hasSubBuildingComponent": "https://w3id.org/rec/core/hasSubBuildingComponent",
    "hasSuperBuildingComponent": "https://w3id.org/rec/core/hasSuperBuildingComponent",
    "isPartOfStorey": "https://w3id.org/rec/core/isPartOf",
    "hasAlias": "http://proptechos.com/ontology/extension/hasAlias"
  },
  "@type": "Room",
  "comment": {},
  "createdTime": "2020-04-15T13:03:39.517Z",
  "geoReferenceOrigo": "27.88;11.72;45.41",
  "hasAlias": [
    {
      "@context": {
        "@vocab": "http://proptechos.com/ontology/extension",
        "Alias": "http://proptechos.com/ontology/extension/Alias",
        "isMemberOfAliasNamespace": "http://proptechos.com/ontology/extension/isMemberOfAliasNamespace"
      },
      "@type": "Alias",
      "isMemberOfAliasNamespace": {
        "@id": "https://foo.proptechos.com/api/aliasnamespace/91fc9574-6dbf-4bd8-9508-6ce1edeb28b2",
        "@type": "@id"
      },
      "@id": "https://mysystem.com/room/40b9c6a8-e738-4da8-a2e1-e661e1977e7a"
    }
  ],
  "hasSubBuildingComponent": {
    "@id": null,
    "@type": "@id"
  },
  "hasSuperBuildingComponent": {
    "@id": null,
    "@type": "@id"
  },
  "isPartOfBuilding": {
    "@id": "https://foo.proptechos.com/api/realestatecomponent/0c4b6fae-da27-4502-ad10-0729e45ab68a",
    "@type": "@id"
  },
  "isPartOfStorey": {
    "@id": "https://foo.proptechos.com/api/storey/efb87367-5f5e-442a-b993-c330110ec6c8",
    "@type": "@id"
  },
  "@id": "https://foo.proptechos.com/api/buildingcomponent/e3d443ac-d30e-435d-afc5-56073dd06856",
  "littera": "1009-G9",
  "popularName": null,
  "roomType": "https://w3id.org/rec/building/GroupRoom",
  "source": {},
  "updatedTime": null
}
```
</details>
Use the Room ID to get the presence sensor:

**Request - Get Sensor by Room and QuantityKind**

```
// line breaks for clarity
GET https://foo.proptechos.com/api/sensor?
buildingcomponent_ids=e3d443ac-d30e-435d-afc5-56073dd06856
&quantity_kinds=Presence
&page=0
&size=1
```
<details>
<summary> Full response</summary>

```
{
  "@context": {
    "@vocab": "http://www.w3.org/ns/hydra/core#",
    "Sensor": "https://w3id.org/rec/device/Sensor",
    "servesBuildingComponent": "https://w3id.org/rec/core/servesBuildingComponent",
    "observesActuator": "https://w3id.org/rec/device/observesActuator",
    "hasSuperDevice": "https://w3id.org/rec/core/hasSuperDevice",
    "isMountedInBuildingComponent": "https://w3id.org/rec/core/isMountedInBuildingComponent",
    "hasAlias": "http://proptechos.com/ontology/extension/hasAlias",
    "servesBuilding": "https://w3id.org/rec/core/servesBuilding"
  },
  "@type": "Collection",
  "member": [
    {
      "@context": {
        "@vocab": "https://w3id.org/rec/device"
      },
      "@type": "Sensor",
      "comment": {},
      "createdTime": "2019-04-09T13:05:18.220Z",
      "deviceMeasurementUnit": "https://w3id.org/rec/core/Boolean",
      "devicePlacementContext": "https://w3id.org/rec/device/IndoorAir",
      "deviceQuantityKind": "https://w3id.org/rec/core/Presence",
      "hasAlias": [
        {
          "@context": {
            "@vocab": "http://proptechos.com/ontology/extension",
            "Alias": "http://proptechos.com/ontology/extension/Alias",
            "isMemberOfAliasNamespace": "http://proptechos.com/ontology/extension/isMemberOfAliasNamespace"
          },
          "@type": "Alias",
          "isMemberOfAliasNamespace": {
            "@id": "https://foo.proptechos.com/api/aliasnamespace/91fc9574-6dbf-4bd8-9508-6ce1edeb28b2",
            "@type": "@id"
          },
          "@id": "https://ns.proptechos.com/another/system/098762983"
        }
      ],
      "hasDeviceFunctionType": null,
      "hasSuperDevice": {
        "@id": "https://foo.proptechos.com/api/device/62e52874-9fa4-41a0-abb2-f4a72e65575a",
        "@type": "@id"
      },
      "isMountedInBuildingComponent": {
        "@id": "https://foo.proptechos.com/api/buildingcomponent/e3d443ac-d30e-435d-afc5-56073dd06856",
        "@type": "@id"
      },
      "@id": "https://foo.proptechos.com/api/sensor/b59d9314-bcb7-4fb6-b30c-8156584f0363",
      "littera": null,
      "observesActuator": {
        "@id": null,
        "@type": "@id"
      },
      "popularName": null,
      "servesBuilding": {
        "@id": null,
        "@type": "@id"
      },
      "servesBuildingComponent": {
        "@id": null,
        "@type": "@id"
      },
      "source": {
        "powerSource": "CommunicationBus"
      },
      "updatedTime": "2019-06-14T13:30:08.677Z"
    }
  ],
  "totalItems": 1,
  "view": {
    "@type": "PartialCollectionView"
  }
}
```
</details>

### Find a ProptechOS object in another system using Alias
#### URI based system - Retrievable Alias
Let’s say you come across a Sensor, that you want to look up further in another system which is called “MyOtherSystem”:
<details>
<summary> Full object</summary>

```
{
  "@context": {
    "@vocab": "https://w3id.org/rec/device",
    "Sensor": "https://w3id.org/rec/device/Sensor",
    "servesBuildingComponent": "https://w3id.org/rec/core/servesBuildingComponent",
    "observesActuator": "https://w3id.org/rec/device/observesActuator",
    "hasSuperDevice": "https://w3id.org/rec/core/hasSuperDevice",
    "isMountedInBuildingComponent": "https://w3id.org/rec/core/isMountedInBuildingComponent",
    "hasAlias": "http://proptechos.com/ontology/extension/hasAlias",
    "servesBuilding": "https://w3id.org/rec/core/servesBuilding"
  },
  "@type": "Sensor",
  "comment": {},
  "createdTime": "2018-06-06T11:22:27.703Z",
  "deviceMeasurementUnit": "https://w3id.org/rec/device/Pascal",
  "devicePlacementContext": "https://w3id.org/rec/device/SupplyAir",
  "deviceQuantityKind": "https://w3id.org/rec/core/Pressure",
  "hasAlias": [
    {
      "@context": {
        "@vocab": "http://proptechos.com/ontology/extension",
        "Alias": "http://proptechos.com/ontology/extension/Alias",
        "isMemberOfAliasNamespace": "http://proptechos.com/ontology/extension/isMemberOfAliasNamespace"
      },
      "@type": "Alias",
      "isMemberOfAliasNamespace": {
        "@id": "https://foo.proptechos.com/api/aliasnamespace/91fc9574-6dbf-4bd8-9508-6ce1edeb28b2",
        "@type": "@id"
      },
      "@id": "https://myothersystem.com/63350079-9c76-4580-a3a2-97b586dae15a"
    }
  ],
  "hasDeviceFunctionType": null,
  "hasSuperDevice": {
    "@id": "https://foo.proptechos.com/api/device/1c09a986-f136-4b51-9a58-7c60a518d513",
    "@type": "@id"
  },
  "isMountedInBuildingComponent": {
    "@id": "https://foo.proptechos.com/api/buildingcomponent/00c21ecc-ef1d-4472-8def-c910fca54739",
    "@type": "@id"
  },
  "@id": "https://foo.proptechos.com/api/sensor/1af49db2-033b-4f09-b286-000452ce7519",
  "littera": null,
  "observesActuator": {
    "@id": null,
    "@type": "@id"
  },
  "popularName": null,
  "servesBuilding": {
    "@id": null,
    "@type": "@id"
  },
  "servesBuildingComponent": {
    "@id": null,
    "@type": "@id"
  },
  "source": {
    "powerSource": "CommunicationBus"
  },
  "updatedTime": "2019-06-14T13:16:15.717Z"
}
```
</details>

```
"hasAlias": [
    {
      "@context": {
        "@vocab": "http://proptechos.com/ontology/extension",
        "Alias": "http://proptechos.com/ontology/extension/Alias",
        "isMemberOfAliasNamespace": "http://proptechos.com/ontology/extension/isMemberOfAliasNamespace"
      },
      "@type": "Alias",
      "isMemberOfAliasNamespace": {
        "@id": "https://foo.proptechos.com/api/aliasnamespace/91fc9574-6dbf-4bd8-9508-6ce1edeb28b2",
        "@type": "@id"
      },
      "@id": "https://myothersystem.com/63350079-9c76-4580-a3a2-97b586dae15a"
    }
  ]
```

To see the Sensor in MyOtherSystem, just follow the URI: `https://myothersystem.com/63350079-9c76-4580-a3a2-97b586dae15a`  
Since _MyOtherSystem_ has objects with URIs, the namespace is retrievable and Alias usage is not more complicated than that.
#### Non-Retrievable Alias
Now, let’s say you come across another Sensor, that you want to look up further in yet another system which is called _“MyOtherNonRetrievableSystem”_ (and which does not have URIs for its objects - it is not retrievable):

<details>
<summary> Full object</summary>

```
{
  "@context": {
    "@vocab": "https://w3id.org/rec/device",
    "Sensor": "https://w3id.org/rec/device/Sensor",
    "servesBuildingComponent": "https://w3id.org/rec/core/servesBuildingComponent",
    "observesActuator": "https://w3id.org/rec/device/observesActuator",
    "hasSuperDevice": "https://w3id.org/rec/core/hasSuperDevice",
    "isMountedInBuildingComponent": "https://w3id.org/rec/core/isMountedInBuildingComponent",
    "hasAlias": "http://proptechos.com/ontology/extension/hasAlias",
    "servesBuilding": "https://w3id.org/rec/core/servesBuilding"
  },
  "@type": "Sensor",
  "comment": {},
  "createdTime": "2019-06-14T12:13:59.180Z",
  "deviceMeasurementUnit": "https://w3id.org/rec/core/Celsius",
  "devicePlacementContext": "https://w3id.org/rec/device/IndoorAir",
  "deviceQuantityKind": "https://w3id.org/rec/core/Temperature",
  "hasAlias": [
    {
      "@context": {
        "@vocab": "http://proptechos.com/ontology/extension",
        "Alias": "http://proptechos.com/ontology/extension/Alias",
        "isMemberOfAliasNamespace": "http://proptechos.com/ontology/extension/isMemberOfAliasNamespace"
      },
      "@type": "Alias",
      "isMemberOfAliasNamespace": {
        "@id": "https://foo.proptechos.com/api/aliasnamespace/91fc9574-6dbf-4bd8-9508-6ce1edeb28b2",
        "@type": "@id"
      },
      "@id": "https://ns.proptechos.com/my_other_system/508-52/62"
    }
  ],
  "hasDeviceFunctionType": null,
  "hasSuperDevice": {
    "@id": "https://foo.proptechos.com/api/device/f8c79a6b-945a-4039-baaf-519346cb68f9",
    "@type": "@id"
  },
  "isMountedInBuildingComponent": {
    "@id": "https://foo.proptechos.com/api/buildingcomponent/dd2e3c64-3eab-40a0-afbb-610efda7490e",
    "@type": "@id"
  },
  "@id": "https://foo.proptechos.com/api/sensor/a8df11d6-e0a9-4c88-a68f-00042c6b1a2a",
  "littera": null,
  "observesActuator": {
    "@id": null,
    "@type": "@id"
  },
  "popularName": null,
  "servesBuilding": {
    "@id": null,
    "@type": "@id"
  },
  "servesBuildingComponent": {
    "@id": null,
    "@type": "@id"
  },
  "source": {
    "powerSource": "CommunicationBus"
  },
  "updatedTime": "2019-06-18T10:19:56.377Z"
}
```
</details>

```
"hasAlias": [
    {
      "@context": {
        "@vocab": "http://proptechos.com/ontology/extension",
        "Alias": "http://proptechos.com/ontology/extension/Alias",
        "isMemberOfAliasNamespace": "http://proptechos.com/ontology/extension/isMemberOfAliasNamespace"
      },
      "@type": "Alias",
      "isMemberOfAliasNamespace": {
        "@id": "https://foo.proptechos.com/api/aliasnamespace/91fc9574-6dbf-4bd8-9508-6ce1edeb28b2",
        "@type": "@id"
      },
      "@id": "https://ns.proptechos.com/my_other_non_retrievable_system/508-52/62"
    }
  ]
```

The Alias `https://ns.proptechos.com/my_other_non_retrievable_system/508-52/62`, which is on the ns.proptechos.com domain, and hence not retrievable.  
If you are familiar with MyOtherNonRetrievableSystem, you might know already what to with the id "508-52/62", otherwise you can look up the AliasNamespace to see instructions and descriptions on how to interpret the Alias.

### Setting up your AliasNamespace
