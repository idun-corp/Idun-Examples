package com.proptechos.model.message;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZonedDateTime;

public class RecObservation {

  private final ZonedDateTime observationTime;
  private final String quantityKind;
  private final String sensorId;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Double value;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String valueString;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Boolean valueBoolean;

  public RecObservation(ZonedDateTime observationTime, Object value, String quantityKind,
      String sensorId) {
    this.observationTime = observationTime;
    this.quantityKind = quantityKind;
    this.sensorId = sensorId;
    setValue(value);
  }

  public ZonedDateTime getObservationTime() {
    return observationTime;
  }

  public Double getValue() {
    return value;
  }

  public String getValueString() {
    return valueString;
  }

  public Boolean getValueBoolean() {
    return valueBoolean;
  }

  public String getQuantityKind() {
    return quantityKind;
  }

  public String getSensorId() {
    return sensorId;
  }

  private void setValue(Object value) {
    if (value instanceof Double) {
      this.value = (Double) value;
    } else if (value instanceof String) {
      this.valueString = (String) value;
    } else if (value instanceof Boolean) {
      this.valueBoolean = (Boolean) value;
    }
  }
}
