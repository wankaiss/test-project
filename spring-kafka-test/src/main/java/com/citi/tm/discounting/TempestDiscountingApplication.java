package com.citi.tm.discounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableConfigurationProperties
@Import(TempestDiscountingContext.class)
public class TempestDiscountingApplication {
  public static void main(String[] args) {
    SpringApplication.run(TempestDiscountingApplication.class, args);
  }
}
