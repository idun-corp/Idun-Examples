package com.proptechos.service.observations;

import com.proptechos.model.message.RecObservation;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

import static com.proptechos.service.actuations.DeviceMessageCallback.actuationValue;
import static java.util.Objects.isNull;

public enum QuantityKind {

    TEMPERATURE("Temperature") {
        @Override
        public RecObservation createObservation(String sensorId) {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                    generateRandomTemperature(), getQuantityKind(), sensorId);
        }
    },
    HUMIDITY("Humidity") {
        @Override
        public RecObservation createObservation(String sensorId) {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                    generateRandomHumidity(), getQuantityKind(), sensorId);
        }
    },
    CO2("CO2") {
        @Override
        public RecObservation createObservation(String sensorId) {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                    generateRandomCO2(), getQuantityKind(), sensorId);
        }
    },
    ALARM_MAJOR("AlarmMajor") {
        @Override
        public RecObservation createObservation(String sensorId) {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                generateRandomBoolean(), getQuantityKind(), sensorId);
        }
    },
    AGENT_INTERACTION("AgentInteraction") {
        @Override
        public RecObservation createObservation(String sensorId) {
            return new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
                generateInteraction(), getQuantityKind(), sensorId);
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

    public static QuantityKind getByLabel(String label) {
        for (QuantityKind qk : QuantityKind.values()) {
            if (qk.quantityKind.equalsIgnoreCase(label)) {
                return qk;
            }
        }
        return null;
    }

    private static final int MIN_TEMPERATURE = 15;
    private static final int MIN_HUMIDITY = 40;
    private static final int MIN_CO2 = 700;
    private static final List<String> INTERACTIONS = List.of("PUSH","PULL","STAY","GO");

    private static final Random random = new Random();

    private static Object generateRandomTemperature() {
        return isNull(actuationValue)
                ? MIN_TEMPERATURE + Math.random() * 15
                : actuationValue;
    }

    private static double generateRandomHumidity() {
        return MIN_HUMIDITY + Math.random() * 30;
    }

    private static double generateRandomCO2() {
        return MIN_CO2 + Math.random() * 500;
    }

    private static boolean generateRandomBoolean() {
        return random.nextBoolean();
    }

    private static String generateInteraction() {
        return INTERACTIONS.get(random.nextInt(INTERACTIONS.size()));
    }

}
