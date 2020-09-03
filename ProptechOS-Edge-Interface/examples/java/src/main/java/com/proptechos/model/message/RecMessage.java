package com.proptechos.model.message;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import com.proptechos.model.message.actuation.RecActuationCommand;
import com.proptechos.model.message.actuation.RecActuationResponse;
import com.proptechos.model.message.exception.RecException;

public class RecMessage {

  private final String format = "rec3.1.1";
  private String deviceId;
  private List<RecObservation> observations;
  private List<RecException> exceptions;
  private List<RecActuationCommand> actuationCommands;
  private List<RecActuationResponse> actuationResponses;

  @JsonIgnore
  private Object edgeStatuses;

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
    return isNull(observations)
        ? emptyList()
        : observations;
  }

  public RecMessage setObservations(List<RecObservation> observations) {
    this.observations = observations;
    return this;
  }

  public List<RecException> getExceptions() {
    return isNull(exceptions)
        ? emptyList()
        : exceptions;
  }

  public List<RecActuationCommand> getActuationCommands() {
    return isNull(actuationCommands)
        ? emptyList()
        : actuationCommands;
  }

  public List<RecActuationResponse> getActuationResponses() {
    return isNull(actuationResponses)
        ? emptyList()
        : actuationResponses;
  }

  public RecMessage setActuationResponses(List<RecActuationResponse> actuationResponses) {
    this.actuationResponses = actuationResponses;
    return this;
  }

  public Object getEdgeStatuses() {
    return edgeStatuses;
  }
}
