package service;

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
    final RecMessage recMessage = new RecMessage();
    final Sensor randomSensor = getRandomSensor();
    final RecObservation observation = new RecObservation(ZonedDateTime.now(ZoneOffset.UTC),
        generateRandomTemperature(),
        randomSensor.getSensorType(), randomSensor.getSensorId());
    recMessage.setDeviceId(deviceConfig.getDeviceId());
    recMessage.setObservations(Collections.singletonList(observation));
    return recMessage;
  }

  private double generateRandomTemperature() {
    return MIN_TEMPERATURE + rand.nextDouble() * 15;
  }

  private Sensor getRandomSensor() {
    int randomIndex = rand.nextInt(deviceConfig.getSensors().size());
    return deviceConfig.getSensors().get(randomIndex);
  }
}
