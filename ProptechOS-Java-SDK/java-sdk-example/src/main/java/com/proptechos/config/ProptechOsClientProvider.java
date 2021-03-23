package com.proptechos.config;

import com.proptechos.ProptechOsClient;
import com.proptechos.clients.StreamingApiServiceClient;
import com.proptechos.model.auth.AuthenticationConfig;
import com.proptechos.model.auth.KafkaConfig;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.StreamingApiService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProptechOsClientProvider {

  private final ProptechOsClient client;

  public ProptechOsClientProvider(RecApiConfig recApiConfig) {
    this.client = ProptechOsClient.applicationClientBuilder(
        recApiConfig.getBaseUrl())
        .authConfig(AuthenticationConfig.builder()
          .clientId(recApiConfig.getClientId())
          .clientSecret(recApiConfig.getClientSecret()).build()).build();
  }

  @Bean
  public ServiceFactory serviceFactory() {
    return client.serviceFactory();
  }

//  @Bean
//  @ConditionalOnClass(StreamingApiConfig.class)
////  @ConditionalOnProperty(prefix = "streaming.api", name = {
////      "event-hub-name", "event-hub-namespace", "event-hub-namespace-shared-access-key"})
//  public StreamingApiService streamingApiService(StreamingApiConfig streamingApiConfig) {
//    return client.serviceFactory().streamingApiService(KafkaConfig.builder()
//        .eventHub(streamingApiConfig.getEventHubName())
//        .eventHubNamespace(streamingApiConfig.getEventHubNamespace())
//        .sharedAccessKey(streamingApiConfig.getEventHubNamespaceSharedAccessKey()).build());
//  }

//  @Bean
//  @ConditionalOnBean(name = "streamingApiService")
//  public StreamingApiServiceClient streamingApiServiceClient(StreamingApiService streamingApiService) {
//    return new StreamingApiServiceClient(streamingApiService);
//  }

}
