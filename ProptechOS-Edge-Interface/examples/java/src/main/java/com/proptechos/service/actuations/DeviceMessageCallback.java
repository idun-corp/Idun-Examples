package com.proptechos.service.actuations;

import static com.proptechos.service.JsonParser.serializeToString;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubMessageResult;
import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.MessageCallback;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.proptechos.model.message.RecMessage;
import com.proptechos.service.JsonParser;

public class DeviceMessageCallback implements MessageCallback {

  private final DeviceClient deviceClient;
  private final DeviceResponseGenerator deviceResponseGenerator;
  public static Object actuationValue;

  public DeviceMessageCallback(DeviceClient deviceClient) {
    this.deviceClient = deviceClient;
    deviceResponseGenerator = new DeviceResponseGenerator();
  }

  @Override
  public IotHubMessageResult execute(Message message, Object o) {
    try {
      RecMessage messageFromCloud = JsonParser.fromBytes(message.getBytes());
      String messageContent = new String(message.getBytes(), StandardCharsets.UTF_8);
      System.out.println("Incoming message: " + messageContent);
      assignActuationValue(messageFromCloud);
      sendDeviceMessage(messageFromCloud);
      return IotHubMessageResult.COMPLETE;
    } catch (IOException e) {
      e.printStackTrace();
      return IotHubMessageResult.REJECT;
    }
  }

  private void sendDeviceMessage(RecMessage messageFromCloud)
      throws JsonProcessingException {
    RecMessage deviceMessage = deviceResponseGenerator
        .getSimulatedMessageFromDevice(messageFromCloud);
    String msgStr = serializeToString(deviceMessage);
    System.out.println("Sending actuation response: " + msgStr);
    deviceClient.sendEventAsync(new Message(msgStr), null, new Object());
  }

  //this method assigns value that is sent from ProptechOS actuation to a static variable in order to
  //change temperature values that will be send in RecObservation object.
  //This simulates real flow of actuation.
  private void assignActuationValue(RecMessage messageFromCloud) {
    messageFromCloud.getActuationCommands().stream()
        .findFirst()
        .ifPresent(value -> actuationValue = value.getValue());
  }
}
