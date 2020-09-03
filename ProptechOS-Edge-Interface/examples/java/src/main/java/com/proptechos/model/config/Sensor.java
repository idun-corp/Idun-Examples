package com.proptechos.model.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sensor {

  private final String sensorId;
  private final String quantityKind;

  @JsonCreator
  public Sensor(@JsonProperty(value = "sensorId") String sensorId,
      @JsonProperty(value = "quantityKind") String quantityKind) {
    this.sensorId = sensorId;
    this.quantityKind = quantityKind;
  }

  public String getSensorId() {
    return sensorId;
  }

  public String getQuantityKind() {
    return quantityKind;
  }
}
