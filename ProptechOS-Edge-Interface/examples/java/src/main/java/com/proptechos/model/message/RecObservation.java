package com.proptechos.model.message;

import java.time.ZonedDateTime;

public class RecObservation {

  private final ZonedDateTime observationTime;
  private final Object value;
  private final String quantityKind;
  private final String sensorId;

  public RecObservation(ZonedDateTime observationTime, Object value, String quantityKind,
      String sensorId) {
    this.observationTime = observationTime;
    this.value = value;
    this.quantityKind = quantityKind;
    this.sensorId = sensorId;
  }

  public ZonedDateTime getObservationTime() {
    return observationTime;
  }

  public Object getValue() {
    return value;
  }

  public String getQuantityKind() {
    return quantityKind;
  }

  public String getSensorId() {
    return sensorId;
  }
}
