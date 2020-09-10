## Device connection checker run guide 
   * Download ProptechOS-device-connection-checker project 
   * run `mvn clean install`
   * Create txt file with device ids in next format:  
    
    56bd567b-1805-41e1-8ad7-032d5e102dd8 
    6071929b-e916-47e4-b1e4-0eebea07ca0b
    5cf324d2-efd3-4f8e-aba4-1dbbd088a4c6
    8d1dec56-f875-47c3-b2a0-219a972200a1
   
   * run the tool, specifying this file and IoT Hub connection sting in input parameters
   
   EXAMPLE : 
   `java -jar device-connection-checker-tool.jar "f:/test/data/device_ids.txt" "YOUR_IOT_HUB_CONNECTION_STRING"`


##Development guide
The intention of this guide is to help a developer to create a tool to check if devices are successfully connected to IoT Hub.
It will be helpful when troubleshooting, to reject sources of errors (like unable to connect).

## Prerequisite

* Have Devices created and registered in IoT Hub
* Java SE Development Kit 11 or higher
* Apache Maven 3

## Implement main() method that run the RegistryManager
Create a simple Java class, call it something that represents your connector. This class should have the `main()` method implemented, that will be a starting point of our application.  
`public static void main(String[] args);`

Implementation of `main(String[] args)` method:
   * create instance of class `RegistryManager` [details](https://docs.microsoft.com/en-us/java/api/com.microsoft.azure.sdk.iot.service.RegistryManager?view=azure-java-stable), it receives IoT Hub connection string as argument
   * call `RegistryManager` method `getDevice(String deviceId)` [details](https://docs.microsoft.com/en-us/java/api/com.microsoft.azure.sdk.iot.service.registrymanager.getdevice?view=azure-java-stable#com_microsoft_azure_sdk_iot_service_RegistryManager_getDevice_java_lang_String_). It is up to you where to store device ids. In our example we store device ids in external txt file. 
   * call `Device` method `getConnectionState()` [details](https://docs.microsoft.com/en-us/java/api/com.microsoft.azure.sdk.iot.service.basedevice.getconnectionstate?view=azure-java-stable#com_microsoft_azure_sdk_iot_service_BaseDevice_getConnectionState__) it will return you enum with actual state of your device(Connected/Disconnected).
   
## Appendix
## Bootstrap new project
1. Create simple, empty java project Using Apache Maven:

```
mvn archetype:generate
-DgroupId={project-packaging}
-DartifactId={project-name} -DarchetypeArtifactId={maven-template} -DinteractiveMode=false
```
Import the project folder you just created with project files to your IDE as a Maven project.

2. Add dependencies to maven project pom.xml

```xml
<dependencies>

    <dependency>
      <groupId>com.microsoft.azure.sdk.iot</groupId>
      <artifactId>iot-service-client</artifactId>
      <version>1.20.1</version>
    </dependency>

</dependencies>
```