package model.message;

import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
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

}
