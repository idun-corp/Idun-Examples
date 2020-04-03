package model.message.actuation;

import java.time.ZonedDateTime;

public class RecActuationCommand {

  private ZonedDateTime actuationCommandTime;
  private String actuationId;
  private String actuationInterfaceId;
  private String actuatorId;
  private Object value;

  public ZonedDateTime getActuationCommandTime() {
    return actuationCommandTime;
  }

  public String getActuationId() {
    return actuationId;
  }

  public String getActuationInterfaceId() {
    return actuationInterfaceId;
  }

  public String getActuatorId() {
    return actuatorId;
  }

  public Object getValue() {
    return value;
  }

}
