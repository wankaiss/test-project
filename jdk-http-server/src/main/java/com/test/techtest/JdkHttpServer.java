package com.test.techtest;

import com.sun.net.httpserver.HttpServer;
import com.test.techtest.resource.ResourceCase;
import org.glassfish.jersey.jdkhttp.JdkHttpHandlerContainerProvider;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JdkHttpServer {
  public static void main(String[] args) {
    URI uri = UriBuilder.fromUri("http://localhost/").port(8081).build();
    HttpServer httpServer = JdkHttpServerFactory.createHttpServer(
            uri,
            new ResourceConfig(ResourceCase.class), false);

    httpServer.start();

  }
}
