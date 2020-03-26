package service;

import static service.ConfigParser.parseToString;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.Message;
import model.config.DeviceConfig;
import model.message.RecMessage;

public class MessageSender implements Runnable {

  private DeviceClient client;
  private TelemetryGenerator telemetryGenerator;

  public MessageSender(DeviceClient client, DeviceConfig deviceConfig) {
    this.client = client;
    telemetryGenerator = new TelemetryGenerator(deviceConfig);
  }

  public void run() {
    try {
      while (true) {
        // Simulate telemetry.
        RecMessage telemetry = telemetryGenerator.generateTelemetry();
        // Add the telemetry to the message body as JSON.
        String msgStr = parseToString(telemetry);
        Message message = new Message(msgStr);
        System.out.println("Sending message: " + msgStr);
        client.sendEventAsync(message, null, new Object());
        Thread.sleep(5000);
      }
    } catch (InterruptedException e) {
      System.out.println("Finished.");
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}
