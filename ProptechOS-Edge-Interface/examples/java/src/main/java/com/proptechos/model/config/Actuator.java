package com.proptechos.model.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Actuator {

  private final String actuatorId;

  @JsonCreator
  public Actuator(@JsonProperty(value = "actuatorId") String actuatorId) {
    this.actuatorId = actuatorId;
  }

  public String getActuatorId() {
    return actuatorId;
  }
}
