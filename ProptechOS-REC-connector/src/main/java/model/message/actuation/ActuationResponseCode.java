package model.message.actuation;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ActuationResponseCode {

  SUCCESS("success"),
  REJECTED("rejected");

  @JsonValue
  private final String statusCode;

  ActuationResponseCode(String statusCode) {
    this.statusCode = statusCode;
  }

}
