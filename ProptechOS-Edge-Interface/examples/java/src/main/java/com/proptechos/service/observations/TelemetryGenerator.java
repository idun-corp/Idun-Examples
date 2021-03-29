package com.proptechos.service.observations;

import static java.util.stream.Collectors.toList;

import com.proptechos.model.config.DeviceConfig;
import com.proptechos.model.config.Sensor;
import com.proptechos.model.message.RecMessage;
import com.proptechos.model.message.RecObservation;

import java.util.*;

public class TelemetryGenerator {

  private DeviceConfig deviceConfig;

  public TelemetryGenerator(DeviceConfig deviceConfig) {
    this.deviceConfig = deviceConfig;
  }

  public RecMessage generateTelemetry() {
    final List<Sensor> sensors = deviceConfig.getSensors();
    List<RecObservation> observations = sensors.stream()
            .map(sensor -> sensor.getQuantityKind().createObservation(sensor.getSensorId()))
            .collect(toList());
    return new RecMessage()
        .setDeviceId(deviceConfig.getDeviceId())
        .setObservations(observations);
  }

  public int getTelemetrySendPeriod() {
    return deviceConfig.getTelemetrySendPeriod();
  }
}
