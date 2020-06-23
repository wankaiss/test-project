package com.springboot.test.config;

import com.sun.net.httpserver.HttpServer;
import com.test.techtest.JerseyConfig;
import lombok.SneakyThrows;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.UriBuilder;

@Component
public class JerseyRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

  @SneakyThrows
  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    JerseyConfig resourceConfig = new JerseyConfig();
    resourceConfig.property("contextConfig", contextRefreshedEvent.getApplicationContext());
    HttpServer httpServer =
        JdkHttpServerFactory.createHttpServer(
            UriBuilder.fromUri("http://localhost/").port(8081).build(), resourceConfig, false);

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  httpServer.stop(0);
                }));

    httpServer.start();
    Thread.currentThread().join();
  }
}
