package com.proptechos.service.actuations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubMessageResult;
import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.MessageCallback;
import com.proptechos.model.config.Actuator;
import com.proptechos.model.message.RecMessage;
import com.proptechos.service.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static com.proptechos.service.JsonParser.serializeToString;
import static java.util.Objects.nonNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class DeviceMessageCallback implements MessageCallback {

    private final DeviceClient deviceClient;
    private final DeviceResponseGenerator deviceResponseGenerator;

    private final Map<String, Actuator> actuatorById;
    public static Object actuationValue;

    public static boolean actuationTriggered;
    public static String latestActuationActuatorId;

    public DeviceMessageCallback(DeviceClient deviceClient, List<Actuator> actuators) {
        this.deviceClient = deviceClient;
        deviceResponseGenerator = new DeviceResponseGenerator();
        this.actuatorById = actuators.stream()
            .collect(toMap(Actuator::getActuatorId, identity()));
    }

    @Override
    public IotHubMessageResult execute(Message message, Object o) {
        try {
            actuationTriggered = true;
            RecMessage messageFromCloud = JsonParser.fromBytes(message.getBytes());
            messageFromCloud.getActuationCommands()
                .forEach(actuationCommand -> {
                    Actuator actuator = actuatorById.get(actuationCommand.getActuatorId());
                    if (nonNull(actuator)) {
                        actuator.setActuationValue(actuationCommand.getValue());
                    }
                });
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
