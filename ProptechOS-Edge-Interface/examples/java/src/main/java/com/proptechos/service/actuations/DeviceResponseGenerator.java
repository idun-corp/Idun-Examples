package com.proptechos.service.actuations;

import static java.util.stream.Collectors.toList;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import com.proptechos.model.message.RecMessage;
import com.proptechos.model.message.actuation.ActuationResponseCode;
import com.proptechos.model.message.actuation.RecActuationCommand;
import com.proptechos.model.message.actuation.RecActuationResponse;

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
