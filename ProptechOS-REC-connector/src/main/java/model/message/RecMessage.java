package model.message;

import static java.util.Collections.emptyList;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
