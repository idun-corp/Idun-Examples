package com.idunrealestate.streamingapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Value("${sensor-file}")
  private String resourceFile;

  private Set<String> sensorIds;

  public KafkaConsumer() {}

  @PostConstruct
  public void init() {
    try (Stream<String> stream = Files.lines(Paths.get(resourceFile))) {
      sensorIds = stream.collect(Collectors.toSet());
    } catch (IOException e) {
      log.error("Failed to read resource file: " + e.getMessage(), e);
      throw new IllegalStateException(e);
    }
  }

  @KafkaListener(topics = "${event.hub.name}")
  public void processMessage(String message) {
    try {
      EventHubMessage observation = objectMapper.readValue(message, EventHubMessage.class);
      if (sensorIds.contains(observation.getObservation().getSensorId().toString())) {
        log.info("Sensor id : " + observation.getObservation().getSensorId().toString());
        log.info("Flowscape presence message: " + observation.getObservation());
      }
    } catch (JsonProcessingException e) {
      log.error("Failed to read observation message: " + message, e);
      throw new IllegalStateException(e);
    }
  }

  @Getter
  @ToString
  @NoArgsConstructor
  private static class EventHubObservation {

    private String value;

    private String quantityKind;

    private UUID sensorId;

    private String observationTime;
  }

  @Getter
  @NoArgsConstructor
  private static class EventHubMessage {

    private EventHubObservation observation;

    private String sensor;
  }

}
