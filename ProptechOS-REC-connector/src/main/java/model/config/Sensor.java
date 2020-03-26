package model.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sensor {

  private final String sensorId;
  private final String sensorType;

  @JsonCreator
  public Sensor(@JsonProperty(value = "sensorId") String sensorId,
      @JsonProperty(value = "sensorType") String sensorType) {
    this.sensorId = sensorId;
    this.sensorType = sensorType;
  }

  public String getSensorId() {
    return sensorId;
  }

  public String getSensorType() {
    return sensorType;
  }
}
