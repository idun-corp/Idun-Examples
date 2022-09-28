package com.proptechos.model.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proptechos.model.message.RecObservation;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Actuator {

    private final String actuatorId;
    private Object actuationValue;

    @JsonCreator
    public Actuator(@JsonProperty(value = "actuatorId") String actuatorId) {
        this.actuatorId = actuatorId;
    }

    public String getActuatorId() {
        return actuatorId;
    }

    public Object getActuationValue() {
        return actuationValue;
    }

    public void setActuationValue(Object actuationValue) {
        this.actuationValue = actuationValue;
    }

    public RecObservation createObservation() {
        return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
            actuationValue, actuatorId);
    }
}
