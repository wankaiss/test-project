package com.test.techtest.config;

import com.sun.net.httpserver.HttpServer;
import com.test.techtest.JerseyConfig;
import com.test.techtest.SpringRequestResourceClass;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SpringRequestResourceClass.class)
public class JerseySpringContext {

  @Autowired ApplicationContext applicationContext;

  @Bean
  public JerseyConfig jerseyConfig() {
    return new JerseyConfig();
  }

  @Bean
  public HttpServer httpServer(JerseyConfig jerseyConfig) throws InterruptedException {
    jerseyConfig.property("contextConfig", applicationContext);
    HttpServer httpServer =
        JdkHttpServerFactory.createHttpServer(
            UriBuilder.fromUri("http://localhost/").port(8081).build(), jerseyConfig, false);

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  httpServer.stop(0);
                }));

    httpServer.start();
    //    Thread.currentThread().join();
    return httpServer;
  }
}
