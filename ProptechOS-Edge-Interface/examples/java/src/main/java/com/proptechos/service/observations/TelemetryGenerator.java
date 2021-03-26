package com.proptechos.service.observations;

import static com.proptechos.service.actuations.DeviceMessageCallback.actuationValue;
import static java.util.Objects.isNull;

import com.proptechos.model.config.DeviceConfig;
import com.proptechos.model.config.Sensor;
import com.proptechos.model.message.RecMessage;
import com.proptechos.model.message.RecObservation;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

public class TelemetryGenerator {

  private DeviceConfig deviceConfig;

  public TelemetryGenerator(DeviceConfig deviceConfig) {
    this.deviceConfig = deviceConfig;
  }

  public RecMessage generateTelemetry() {
    List<RecObservation> observations = Arrays.asList(QuantityKind.Temperature.getObservation(),
                                                      QuantityKind.Humidity.getObservation(),
                                                      QuantityKind.CO2.getObservation());
    return new RecMessage()
        .setDeviceId(deviceConfig.getDeviceId())
        .setObservations(observations);
  }

  public int getTelemetrySendPeriod() {
    return deviceConfig.getTelemetrySendPeriod();
  }
}
