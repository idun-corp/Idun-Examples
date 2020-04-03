package model.config;

import static java.lang.String.join;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DeviceConfig {

  private String iotHubAddress;
  private String deviceId;
  private String deviceKey;
  private List<Sensor> sensors;
  private List<Actuator> actuators;

  @JsonCreator
  public DeviceConfig(@JsonProperty(value = "iotHubAddress") String iotHubAddress,
      @JsonProperty(value = "deviceId") String deviceId,
      @JsonProperty(value = "deviceKey") String deviceKey,
      @JsonProperty(value = "sensors") List<Sensor> sensors,
      @JsonProperty(value = "actuators") List<Actuator> actuators) {
    this.iotHubAddress = iotHubAddress;
    this.deviceId = deviceId;
    this.deviceKey = deviceKey;
    this.sensors = sensors;
    this.actuators = actuators;
  }

  public String createConnectionString() {
    String hostName = "HostName=" + iotHubAddress;
    String deviceConnection = "DeviceId=" + deviceId;
    String sharedAccessKey = "SharedAccessKey=" + deviceKey;
    return join(";", hostName, deviceConnection, sharedAccessKey);
  }

  public String getDeviceId() {
    return deviceId;
  }

  public List<Sensor> getSensors() {
    return sensors;
  }

  public List<Actuator> getActuators() {
    return actuators;
  }
}
