package com.proptechos;

import static com.microsoft.azure.sdk.iot.device.IotHubClientProtocol.MQTT;
import static java.util.stream.Collectors.joining;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.MessageCallback;
import com.proptechos.model.config.DeviceConfig;
import com.proptechos.service.JsonParser;
import com.proptechos.service.actuations.DeviceMessageCallback;
import com.proptechos.service.observations.MessageSender;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulatedDeviceApplication {

  public static void main(String[] args) throws IOException, URISyntaxException {

    String pathToConfigFile = args[0];
    String config = Files.lines(Paths.get(pathToConfigFile)).collect(joining());
    DeviceConfig deviceConfig = JsonParser.parse(config);

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

  }

}
