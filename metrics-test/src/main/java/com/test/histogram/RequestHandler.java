package com.test.histogram;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import static com.codahale.metrics.MetricRegistry.name;

public class RequestHandler {
//  private final Histogram histogram;
  private final Timer timer;

  public RequestHandler(MetricRegistry registry, String name) {
//    histogram = registry.histogram(name(RequestHandler.class, name));
    timer = registry.timer(name(RequestHandler.class, name));
  }

//  public void handleRequest(Integer value) {
//    histogram.update(value);
//  }

  public String handleTimerRequest() {
    try (final Timer.Context context = timer.time()) {
      return "OK";
    } catch (Exception e) {
      // Do nothing
    }
    return null;
  }

}
