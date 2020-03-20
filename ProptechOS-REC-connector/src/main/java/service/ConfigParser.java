package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.net.URL;
import model.config.DeviceConfig;
import model.message.RecMessage;

public class ConfigParser {

  private static final ObjectMapper MAPPER =
      new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
          .enable(
              DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES,
              DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES
          );

  public static DeviceConfig parse(URL source) {
    try {
      return MAPPER.readValue(source, DeviceConfig.class);
    } catch (IOException e) {
      throw new IllegalStateException("Exception occurred during configuration reading", e);
    }
  }

  public static String parseToString(RecMessage message) throws JsonProcessingException {
    return MAPPER.writeValueAsString(message);
  }

}