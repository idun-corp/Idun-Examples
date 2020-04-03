package service.observations;

import static java.util.Objects.isNull;
import static service.actuations.DeviceMessageCallback.actuationValue;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Random;
import model.config.DeviceConfig;
import model.config.Sensor;
import model.message.RecMessage;
import model.message.RecObservation;

public class TelemetryGenerator {

  private DeviceConfig deviceConfig;
  private Random rand;

  private static final int MIN_TEMPERATURE = 15;

  public TelemetryGenerator(DeviceConfig deviceConfig) {
    this.deviceConfig = deviceConfig;
    this.rand = new Random();
  }

  public RecMessage generateTelemetry() {
    final Sensor randomSensor = getRandomSensor();
    //we use temperature from actuation, in case
    //when there was no actuation from ProptechOS we use some randomly generated one
    Object temperatureValue = isNull(actuationValue)
        ? generateRandomTemperature()
        : actuationValue;
    final RecObservation observation = new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
        temperatureValue, randomSensor.getQuantityKind(), randomSensor.getSensorId());
    return new RecMessage()
        .setDeviceId(deviceConfig.getDeviceId())
        .setObservations(Collections.singletonList(observation));
  }

  private double generateRandomTemperature() {
    return MIN_TEMPERATURE + rand.nextDouble() * 15;
  }

  private Sensor getRandomSensor() {
    int randomIndex = rand.nextInt(deviceConfig.getSensors().size());
    return deviceConfig.getSensors().get(randomIndex);
  }
}
