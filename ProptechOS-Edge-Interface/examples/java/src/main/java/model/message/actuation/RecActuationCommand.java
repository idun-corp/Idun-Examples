package model.message.actuation;

import java.time.ZonedDateTime;

public class RecActuationCommand {

  private ZonedDateTime actuationCommandTime;
  private String actuationCommandId;
  private String actuatorId;
  private Object value;

  public ZonedDateTime getActuationCommandTime() {
    return actuationCommandTime;
  }

  public String getActuationCommandId() {
    return actuationCommandId;
  }

  public String getActuatorId() {
    return actuatorId;
  }

  public Object getValue() {
    return value;
  }

}
