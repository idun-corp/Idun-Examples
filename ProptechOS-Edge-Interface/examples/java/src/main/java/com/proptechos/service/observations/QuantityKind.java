package com.proptechos.service.observations;

import com.proptechos.model.message.RecObservation;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static com.proptechos.service.actuations.DeviceMessageCallback.actuationValue;
import static java.util.Objects.isNull;

public enum QuantityKind {

    TEMPERATURE("Temperature") {
        @Override
        public RecObservation createObservation(String sensorId) {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                    temperatureValue, getQuantityKind(), sensorId);
        }
    },
    HUMIDITY("Humidity") {
        @Override
        public RecObservation createObservation(String sensorId) {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                    humidityValue, getQuantityKind(), sensorId);
        }
    },
    CO2("CO2") {
        @Override
        public RecObservation createObservation(String sensorId) {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                    co2Value, getQuantityKind(), sensorId);
        }
    };

    private final String quantityKind;

    QuantityKind(String quantityKind) {
        this.quantityKind = quantityKind;
    }

    public String getQuantityKind() {
        return quantityKind;
    }

    public abstract RecObservation createObservation(String sensorId);

    private static final int MIN_TEMPERATURE = 15;
    private static final int MIN_HUMIDITY = 40;
    private static final int MIN_CO2 = 700;

    Object temperatureValue = isNull(actuationValue) ? generateRandomTemperature() : actuationValue;
    Object humidityValue = generateRandomHumidity();
    Object co2Value = generateRandomCO2();

    private double generateRandomTemperature() { return MIN_TEMPERATURE +  Math.random() * 15; }
    private double generateRandomHumidity() {
        return MIN_HUMIDITY + Math.random() * 30;
    }
    private double generateRandomCO2() {
        return MIN_CO2 + Math.random() * 500;
    }

}
