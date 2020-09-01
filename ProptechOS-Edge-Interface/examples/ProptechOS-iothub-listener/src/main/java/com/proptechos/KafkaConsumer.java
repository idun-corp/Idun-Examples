package com.proptechos;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

  @KafkaListener(topics = "${event.hub.name}")
  public void processMessage(String message) {
    System.out.println(message);
  }

}
