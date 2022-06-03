package com.proptechos.model.message;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZonedDateTime;
import java.util.regex.Pattern;

public class RecObservation {

    private final ZonedDateTime observationTime;
    private final String quantityKind;
    private final String sensorId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Number value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String valueString;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean valueBoolean;

    public RecObservation(ZonedDateTime observationTime, Object value, String sensorId) {
        this(observationTime, value, null, sensorId);
    }

    public RecObservation(ZonedDateTime observationTime, Object value, String quantityKind,
                          String sensorId) {
        this.observationTime = observationTime;
        this.quantityKind = quantityKind;
        this.sensorId = sensorId;
        setValue(value);
    }

    public ZonedDateTime getObservationTime() {
        return observationTime;
    }

    public Number getValue() {
        return value;
    }

    public String getValueString() {
        return valueString;
    }

    public Boolean getValueBoolean() {
        return valueBoolean;
    }

    public String getQuantityKind() {
        return quantityKind;
    }

    public String getSensorId() {
        return sensorId;
    }

    private static final Pattern NUMERIC = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static final Pattern BOOLEAN = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);

    private void setValue(Object value) {
        Object resultValue = value instanceof String
            ? handleString((String) value) : value;
        if (resultValue instanceof Number) {
            this.value = (Number) resultValue;
        } else if (resultValue instanceof Boolean) {
            this.valueBoolean = (Boolean) resultValue;
        } else if (resultValue instanceof String) {
            this.valueString = (String) resultValue;
        }
    }

    private Object handleString(String value) {
        if (NUMERIC.matcher(value).matches()) {
            return Double.valueOf(value);
        }
        if (BOOLEAN.matcher(value).matches()) {
            return Boolean.parseBoolean(value);
        }
        return value;
    }
}
