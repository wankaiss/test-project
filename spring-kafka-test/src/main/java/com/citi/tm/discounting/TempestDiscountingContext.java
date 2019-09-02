package com.citi.tm.discounting;

import com.citi.tm.discounting.override.TempestOverrideContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties
@Import(TempestOverrideContext.class)
public class TempestDiscountingContext {}
