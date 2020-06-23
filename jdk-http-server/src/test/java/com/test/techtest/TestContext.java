package com.test.techtest;

import com.test.techtest.config.JerseySpringContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JerseySpringContext.class)
// @ComponentScan(
//    excludeFilters =
//        @ComponentScan.Filter(
//            type = FilterType.ASSIGNABLE_TYPE,
//            value = JerseyRefreshListener.class))
public class TestContext {

  /* @Bean
  @Primary
  public ContextRefreshedEvent contextRefreshedEvent() {
    return mock(ContextRefreshedEvent.class);
  }

  @Bean
  @Primary
  public ApplicationListener<ContextRefreshedEvent> listener() {
    return mock(ApplicationListener.class);
  }*/
}
