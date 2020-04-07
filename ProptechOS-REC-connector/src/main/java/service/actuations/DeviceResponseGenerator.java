package service.actuations;

import static java.util.stream.Collectors.toList;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import model.message.RecMessage;
import model.message.actuation.ActuationResponseCode;
import model.message.actuation.RecActuationCommand;
import model.message.actuation.RecActuationResponse;

public class DeviceResponseGenerator {

  public RecMessage getSimulatedMessageFromDevice(RecMessage messageFromCloud) {
    List<RecActuationResponse> recActuationResponses = messageFromCloud.getActuationCommands()
        .stream()
        .map(this::generateActuationResponse)
        .collect(toList());
    return new RecMessage()
        .setDeviceId(messageFromCloud.getDeviceId())
        .setActuationResponses(recActuationResponses);
  }

  private RecActuationResponse generateActuationResponse(RecActuationCommand actuationCommand) {
    return new RecActuationResponse()
        .setActuationCommandId(actuationCommand.getActuationCommandId())
        .setActuationResponseTime(ZonedDateTime.now(ZoneOffset.UTC))
        .setActuatorId(actuationCommand.getActuatorId())
        .setResponseCode(ActuationResponseCode.SUCCESS);

  }

}
