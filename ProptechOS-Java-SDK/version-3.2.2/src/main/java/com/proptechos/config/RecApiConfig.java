package com.proptechos.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rec.api")
public class RecApiConfig {

  private String baseUrl;

  private String clientId;

  private String clientSecret;

}
