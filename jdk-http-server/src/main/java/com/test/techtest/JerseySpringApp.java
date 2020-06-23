package com.test.techtest;

import com.sun.net.httpserver.HttpServer;
import com.test.techtest.config.JerseySpringContext;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JerseySpringApp {
  public static void main(String[] args) throws InterruptedException {
    JerseyConfig resourceConfig = new JerseyConfig();
    resourceConfig.property(
        "contextConfig", new AnnotationConfigApplicationContext(JerseySpringContext.class));
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
