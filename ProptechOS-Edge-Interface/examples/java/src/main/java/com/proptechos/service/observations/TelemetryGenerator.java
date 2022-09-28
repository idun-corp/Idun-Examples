package com.proptechos.service.observations;

import com.proptechos.model.config.DeviceConfig;
import com.proptechos.model.config.Sensor;
import com.proptechos.model.message.RecMessage;
import com.proptechos.model.message.RecObservation;

import java.util.List;

import static com.proptechos.service.actuations.DeviceMessageCallback.actuationTriggered;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

public class TelemetryGenerator {

    private final DeviceConfig deviceConfig;

    public TelemetryGenerator(DeviceConfig deviceConfig) {
        this.deviceConfig = deviceConfig;
    }

    public RecMessage generateTelemetry() {
        final List<Sensor> sensors = deviceConfig.getSensors();
        List<RecObservation> observations = sensors.stream()
            .map(sensor -> sensor.getQuantityKind().createObservation(sensor.getSensorId()))
            .collect(toList());
        if (actuationTriggered) {
            deviceConfig.getActuators().stream()
                .filter(actuator -> nonNull(actuator.getActuationValue()))
                .forEach(actuator -> observations.add(actuator.createObservation()));
        }
        return new RecMessage()
            .setDeviceId(deviceConfig.getDeviceId())
            .setObservations(observations);
    }

    public int getTelemetrySendPeriod() {
        return deviceConfig.getTelemetrySendPeriod();
    }
}
