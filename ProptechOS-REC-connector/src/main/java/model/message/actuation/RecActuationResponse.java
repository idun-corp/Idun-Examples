package model.message.actuation;

import java.time.ZonedDateTime;

public class RecActuationResponse {

  private String actuatorId;
  private String actuationCommandId;
  private ActuationResponseCode responseCode;
  private ZonedDateTime actuationResponseTime;

  public String getActuatorId() {
    return actuatorId;
  }

  public RecActuationResponse setActuatorId(String actuatorId) {
    this.actuatorId = actuatorId;
    return this;
  }

  public String getActuationCommandId() {
    return actuationCommandId;
  }

  public RecActuationResponse setActuationCommandId(String actuationCommandId) {
    this.actuationCommandId = actuationCommandId;
    return this;
  }

  public ActuationResponseCode getResponseCode() {
    return responseCode;
  }

  public RecActuationResponse setResponseCode(ActuationResponseCode responseCode) {
    this.responseCode = responseCode;
    return this;
  }

  public ZonedDateTime getActuationResponseTime() {
    return actuationResponseTime;
  }

  public RecActuationResponse setActuationResponseTime(ZonedDateTime actuationResponseTime) {
    this.actuationResponseTime = actuationResponseTime;
    return this;
  }
}
