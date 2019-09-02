package com.citi.tm.discounting.override;

import com.citi.tm.discounting.override.configuration.KafkaStreamsExampleConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(KafkaStreamsExampleConfiguration.class)
public class TempestOverrideContext {}
