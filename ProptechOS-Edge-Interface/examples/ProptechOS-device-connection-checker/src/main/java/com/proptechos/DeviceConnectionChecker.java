package com.proptechos;

import static java.lang.String.format;

import com.microsoft.azure.sdk.iot.service.Device;
import com.microsoft.azure.sdk.iot.service.RegistryManager;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DeviceConnectionChecker {

  public static void main(String[] args) {
    if (args.length == 2) {
      String pathToFile = args[0];
      String iotHubConnectionString = args[1];
      try (Stream<String> stream = Files.lines(Paths.get(pathToFile))) {
        RegistryManager registryManager = RegistryManager
            .createFromConnectionString(iotHubConnectionString);
        stream.forEach(line -> checkDeviceConnection(registryManager, line));
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      throw new IllegalArgumentException("You should specify two parameters. "
          + "Path to file with device ids, and iot hub connection string");
    }
  }

  private static void checkDeviceConnection(RegistryManager registryManager, String line) {
    try {
      Device device = registryManager.getDevice(line);
      System.out.println(
          format("Device %s is - " + device.getConnectionState(), device.getDeviceId()));
    } catch (IOException | IotHubException e) {
      e.printStackTrace();
    }
  }

}
