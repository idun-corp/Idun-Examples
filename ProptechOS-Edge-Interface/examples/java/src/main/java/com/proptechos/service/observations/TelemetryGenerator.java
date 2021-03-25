package com.proptechos.service.observations;

import static com.proptechos.service.actuations.DeviceMessageCallback.actuationValue;
import static java.util.Objects.isNull;

import com.proptechos.model.config.DeviceConfig;
import com.proptechos.model.config.Sensor;
import com.proptechos.model.message.RecMessage;
import com.proptechos.model.message.RecObservation;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TelemetryGenerator {

  private DeviceConfig deviceConfig;
  private Random rand;

  private static final int MIN_TEMPERATURE = 15;
  private static final int MIN_HUMIDITY = 40;
  private static final int MIN_CO2 = 700;

  private static final String TEMPERATURE_SENSOR_QUANTITY_KIND = "Temperature";
  private static final String HUMIDITY_SENSOR_QUANTITY_KIND = "Humidity";
  private static final String CO2_SENSOR_QUANTITY_KIND = "CO2";

  public TelemetryGenerator(DeviceConfig deviceConfig) {
    this.deviceConfig = deviceConfig;
    this.rand = new Random();
  }

  public RecMessage generateTelemetry() {
    final Sensor temperatureSensor = getTemperatureSensor();
    final Sensor humiditySensor = getHumiditySensor();
    final Sensor co2Sensor = getCO2Sensor();
    //we use temperature from actuation, in case
    //when there was no actuation from ProptechOS we use some randomly generated one
    Object temperatureValue = isNull(actuationValue)
        ? generateRandomTemperature()
        : actuationValue;
    // for humidity we use randomly generated values
    Object humidityValue = generateRandomHumidity();
    // for CO2 we use randomly generated values
    Object co2Value = generateRandomCO2();
    final RecObservation temperetureObservation = new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
        temperatureValue, temperatureSensor.getQuantityKind(), temperatureSensor.getSensorId());
    final RecObservation humidityObservation = new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
            humidityValue, humiditySensor.getQuantityKind(), humiditySensor.getSensorId());
    final RecObservation co2Observation = new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
            co2Value, co2Sensor.getQuantityKind(), co2Sensor.getSensorId());
    List<RecObservation> observations = new ArrayList<>();
    observations.add(temperetureObservation);
    observations.add(humidityObservation);
    observations.add(co2Observation);
    return new RecMessage()
        .setDeviceId(deviceConfig.getDeviceId())
        .setObservations(observations);
  }

  private double generateRandomTemperature() {
    return MIN_TEMPERATURE + rand.nextDouble() * 15;
  }

  private double generateRandomHumidity() {
    return MIN_HUMIDITY + rand.nextDouble() * 30;
  }

  private double generateRandomCO2() {
    return MIN_CO2 + rand.nextDouble() * 500;
  }

  private Sensor getTemperatureSensor() {
    return deviceConfig.getSensors().stream()
            .filter(s -> s.getQuantityKind().equals(TEMPERATURE_SENSOR_QUANTITY_KIND))
            .findFirst()
            .get();
  }

  private Sensor getHumiditySensor() {
    return deviceConfig.getSensors().stream()
            .filter(s -> s.getQuantityKind().equals(HUMIDITY_SENSOR_QUANTITY_KIND))
            .findFirst()
            .get();
  }

  private Sensor getCO2Sensor() {
    return deviceConfig.getSensors().stream()
            .filter(s -> s.getQuantityKind().equals(CO2_SENSOR_QUANTITY_KIND))
            .findFirst()
            .get();
  }

  public int getTelemetrySendPeriod() {
    return deviceConfig.getTelemetrySendPeriod();
  }
}
