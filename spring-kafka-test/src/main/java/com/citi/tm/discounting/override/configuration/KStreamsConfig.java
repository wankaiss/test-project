package com.citi.tm.discounting.override.configuration;

import java.util.HashMap;
import java.util.Map;
import lombok.Setter;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@ConfigurationProperties(prefix = "override.kafka.streams")
public class KStreamsConfig {
  private String applicationId;
  private String bootstrapServers;

  public Map<String, Object> getPropertyMap() {
    HashMap<String, Object> props = new HashMap<>();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return props;
  }
}
