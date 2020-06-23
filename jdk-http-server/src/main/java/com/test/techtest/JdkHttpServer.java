package com.test.techtest;

import com.sun.net.httpserver.HttpServer;
import com.test.techtest.resource.ResourceCase;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class JdkHttpServer {
  public static void main(String[] args) {
    URI uri = UriBuilder.fromUri("http://localhost/").port(8081).build();
    HttpServer httpServer =
        JdkHttpServerFactory.createHttpServer(uri, new ResourceConfig(ResourceCase.class), false);

    httpServer.start();
  }
}
