package com.idunrealestate.streamingapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

  @KafkaListener(topics = "${event.hub.name}")
  public void processMessage(String message) {
    log.info("Streaming API message: " + message);
  }

}
