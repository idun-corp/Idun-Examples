import static com.microsoft.azure.sdk.iot.device.IotHubClientProtocol.MQTT;
import static java.lang.Thread.currentThread;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import model.config.DeviceConfig;
import service.ConfigParser;
import service.MessageSender;

public class SimulatedDeviceApplication {

  public static void main(String[] args) throws IOException, URISyntaxException {

    URL resource = currentThread().getContextClassLoader().getResource("device_config.json");
    DeviceConfig deviceConfig = ConfigParser.parse(resource);

    // Connect to the IoT hub.
    DeviceClient client = new DeviceClient(deviceConfig.createConnectionString(), MQTT);
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
