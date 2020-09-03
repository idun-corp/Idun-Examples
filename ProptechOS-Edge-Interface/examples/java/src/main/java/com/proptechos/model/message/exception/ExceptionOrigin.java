package com.proptechos.model.message.exception;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExceptionOrigin {

  SENSOR("sensor"),
  ACTUATOR("actuator");

  @JsonValue
  private final String origin;

  ExceptionOrigin(String origin) {
    this.origin = origin;
  }
}
