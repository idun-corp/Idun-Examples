package service;

import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;

public class EventCallback implements IotHubEventCallback {

  // Print the acknowledgement received from IoT Hub for the telemetry message sent.
  public void execute(IotHubStatusCode status, Object context) {
    if (context != null) {
      synchronized (context) {
        context.notify();
      }
    }
  }

}
