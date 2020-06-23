package com.test.techtest;

import java.lang.annotation.Annotation;
import javax.ws.rs.Path;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.reflections.Reflections;

public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    register(RequestContextFilter.class);
    scanPackages(Path.class, "com.test.techtest");
    register(LoggingFeature.class);
  }

  public void scanPackages(Class<? extends Annotation> annotation, String... packages) {
    Reflections reflections = new Reflections(packages);
    reflections.getTypesAnnotatedWith(annotation).forEach(this::register);
  }
}
