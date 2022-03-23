package com.proptechos.model.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proptechos.service.observations.QuantityKind;

import java.util.Locale;

public class Sensor {

  private final String sensorId;
  private final QuantityKind quantityKind;

  @JsonCreator
  public Sensor(@JsonProperty(value = "sensorId") String sensorId,
                @JsonProperty(value = "quantityKind") String quantityKind) {
    this.sensorId = sensorId;
    this.quantityKind = QuantityKind.getByLabel(quantityKind);
  }

  public String getSensorId() {
    return sensorId;
  }

  public QuantityKind getQuantityKind() {
    return quantityKind;
  }
}
