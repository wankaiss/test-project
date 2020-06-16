package com.test.techtest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("metrics")
public class ResourceCase {

  @GET
  public String getMetricsOne() {
    return "MetricsThree";
  }
}
