package com.proptechos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.net.URL;
import com.proptechos.model.config.DeviceConfig;
import com.proptechos.model.message.RecMessage;

public class JsonParser {

  private static final ObjectMapper MAPPER =
      new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
          .enable(
              DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES,
              DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES
          );

  public static RecMessage fromBytes(byte[] bytes) throws IOException {
    return MAPPER.readValue(bytes, RecMessage.class);
  }

  public static DeviceConfig parse(String source) {
    try {
      return MAPPER.readValue(source, DeviceConfig.class);
    } catch (IOException e) {
      throw new IllegalStateException("Exception occurred during configuration reading", e);
    }
  }

  public static String serializeToString(RecMessage message) throws JsonProcessingException {
    return MAPPER.writeValueAsString(message);
  }

}
