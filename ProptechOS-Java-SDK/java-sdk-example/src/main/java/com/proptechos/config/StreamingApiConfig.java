package com.proptechos.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "streaming.api")
@ConditionalOnProperty(prefix = "streaming.api", name = {
    "event-hub-name", "event-hub-namespace", "event-hub-namespace-shared-access-key"})
public class StreamingApiConfig {

  private String eventHubName;

  private String eventHubNamespace;

  private String eventHubNamespaceSharedAccessKey;

}
