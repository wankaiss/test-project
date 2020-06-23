package com.test.techtest;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.reflections.Reflections;

import javax.ws.rs.Path;
import java.lang.annotation.Annotation;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(RequestContextFilter.class);
//        packages("com.test.techtest");
        scanPackages(Path.class, "com.test.techtest");
        register(LoggingFeature.class);
    }

  public void scanPackages(Class<? extends Annotation> annotation, String... packages) {
      Reflections reflections = new Reflections(packages);
      reflections.getTypesAnnotatedWith(annotation).forEach(this::register);
  }
}
