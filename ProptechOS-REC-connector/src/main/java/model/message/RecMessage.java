package model.message;

import static java.util.Collections.emptyList;

import java.util.List;

public class RecMessage {

  private final String format = "rec3.2";
  private String deviceId;
  private List<RecObservation> observations;

  public RecMessage() {
    this("", emptyList());
  }

  public RecMessage(String deviceId,
      List<RecObservation> observations) {
    this.deviceId = deviceId;
    this.observations = observations;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public void setObservations(List<RecObservation> observations) {
    this.observations = observations;
  }

  public String getFormat() {
    return format;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public List<RecObservation> getObservations() {
    return observations;
  }
}
