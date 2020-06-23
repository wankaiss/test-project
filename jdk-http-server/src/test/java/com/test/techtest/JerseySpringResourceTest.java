package com.test.techtest;

import static org.junit.Assert.assertEquals;

import com.sun.net.httpserver.HttpServer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class JerseySpringResourceTest {

  @Autowired ApplicationContext applicationContext;

  @Autowired HttpServer httpServer;

  @Before
  public void setup() {
    //    httpServer.start();
  }

  @Test
  public void testGreet() {
    // given
    Client client = ClientBuilder.newBuilder().build();

    // when
    String metrics =
        client.target("http://localhost:8081/").path("metrics").request().get(String.class);

    assertEquals("MetricsThree", metrics);
  }

  @After
  public void teardown() {
    HttpServer httpServer = (HttpServer) applicationContext.getBean("httpServer");
    httpServer.stop(0);
  }
}
