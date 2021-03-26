package com.proptechos.service.observations;

import com.proptechos.model.config.DeviceConfig;
import com.proptechos.model.config.Sensor;
import com.proptechos.model.message.RecObservation;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static com.proptechos.service.actuations.DeviceMessageCallback.actuationValue;
import static java.util.Objects.isNull;

public enum QuantityKind {

    Temperature {
        @Override
        public Sensor getSensor() {
            return deviceConfig.getSensors().stream()
                    .filter(s -> s.getQuantityKind().equals(String.valueOf(Temperature)))
                    .findFirst()
                    .get();
        }

        @Override
        public RecObservation getObservation() {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                    temperatureValue, String.valueOf(Temperature), getSensor().getSensorId());
        }
    },
    Humidity {
        @Override
        public Sensor getSensor() {
            return deviceConfig.getSensors().stream()
                    .filter(s -> s.getQuantityKind().equals(String.valueOf(Humidity)))
                    .findFirst()
                    .get();
        }

        @Override
        public RecObservation getObservation() {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                    humidityValue, String.valueOf(Humidity), getSensor().getSensorId());
        }
    },
    CO2 {
        @Override
        public Sensor getSensor() {
            return deviceConfig.getSensors().stream()
                    .filter(s -> s.getQuantityKind().equals(String.valueOf(CO2)))
                    .findFirst()
                    .get();
        }

        @Override
        public RecObservation getObservation() {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                    co2Value, String.valueOf(CO2), getSensor().getSensorId());
        }
    };

    private static DeviceConfig deviceConfig;
    public static void setDeviceConfig(DeviceConfig deviceConfig) {
        QuantityKind.deviceConfig = deviceConfig;
    }

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

    public abstract Sensor getSensor();
    public abstract RecObservation getObservation();

}
