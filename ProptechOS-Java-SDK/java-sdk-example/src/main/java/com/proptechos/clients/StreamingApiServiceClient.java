package com.proptechos.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proptechos.config.StreamingApiConfig;
import com.proptechos.model.auth.KafkaConfig;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.StreamingApiService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(StreamingApiConfig.class)
public class StreamingApiServiceClient {

  private final StreamingApiService streamingApiService;

  public StreamingApiServiceClient(
      StreamingApiConfig streamingApiConfig,
      ServiceFactory serviceFactory) {
    this.streamingApiService = serviceFactory.streamingApiService(KafkaConfig.builder()
      .eventHub(streamingApiConfig.getEventHubName())
      .eventHubNamespace(streamingApiConfig.getEventHubNamespace())
      .sharedAccessKey(streamingApiConfig.getEventHubNamespaceSharedAccessKey()).build());
  }

  public void subscribeOnObservations() {
    ObjectMapper mapper = new ObjectMapper();
    streamingApiService.subscribe(observation -> {
      try {
        mapper.writeValueAsString(observation);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });
  }

}
