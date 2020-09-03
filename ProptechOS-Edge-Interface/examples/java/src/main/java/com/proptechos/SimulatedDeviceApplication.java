package com.proptechos;

import static com.microsoft.azure.sdk.iot.device.IotHubClientProtocol.MQTT;
import static java.lang.Thread.currentThread;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.MessageCallback;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.proptechos.model.config.DeviceConfig;
import com.proptechos.service.JsonParser;
import com.proptechos.service.actuations.DeviceMessageCallback;
import com.proptechos.service.observations.MessageSender;

public class SimulatedDeviceApplication {

  public static void main(String[] args) throws IOException, URISyntaxException {

    URL resource = currentThread().getContextClassLoader().getResource("device_config.json");
    DeviceConfig deviceConfig = JsonParser.parse(resource);

    // Connect to the IoT hub.
    DeviceClient client = new DeviceClient(deviceConfig.createConnectionString(), MQTT);

    //add callback to receive messages from ProptechOS
    MessageCallback callback = new DeviceMessageCallback(client);
    client.setMessageCallback(callback, null);
    client.open();

    // Create new thread and start sending messages
    MessageSender sender = new MessageSender(client, deviceConfig);
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(sender);

    // Stop the application.
    System.out.println("Press ENTER to exit.");
    System.in.read();
    executor.shutdownNow();
    client.closeNow();
  }

}
