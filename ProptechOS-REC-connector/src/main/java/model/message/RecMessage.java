package model.message;

import java.util.List;
import model.message.actuation.RecActuationCommand;
import model.message.actuation.RecActuationResponse;
import model.message.exception.RecException;

public class RecMessage {

  private final String format = "rec3.2";
  private String deviceId;
  private List<RecObservation> observations;
  private List<RecException> exceptions;
  private List<RecActuationCommand> actuationCommands;
  private List<RecActuationResponse> actuationResponses;

  public String getFormat() {
    return format;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public RecMessage setDeviceId(String deviceId) {
    this.deviceId = deviceId;
    return this;
  }

  public List<RecObservation> getObservations() {
    return observations;
  }

  public RecMessage setObservations(List<RecObservation> observations) {
    this.observations = observations;
    return this;
  }

  public List<RecException> getExceptions() {
    return exceptions;
  }

  public List<RecActuationCommand> getActuationCommands() {
    return actuationCommands;
  }

  public List<RecActuationResponse> getActuationResponses() {
    return actuationResponses;
  }

  public RecMessage setActuationResponses(List<RecActuationResponse> actuationResponses) {
    this.actuationResponses = actuationResponses;
    return this;
  }

}
