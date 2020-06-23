package com.test;

import com.codahale.metrics.*;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.test.gauge.QueueManager;
import com.test.healthcheck.DatabaseHealthCheck;
import com.test.histogram.RequestHandler;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

public class GetStarted {
  static final MetricRegistry metrics = new MetricRegistry();
  static final HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

  public static void main(String args[]) {
    startReport();
    //    healthChecks();
    //    meterMetrics();
    counterMetrics();
    //    histogramAndTimerMetrics();
    wait5Seconds();
  }

  private static void histogramAndTimerMetrics() {
    RequestHandler requestHandler = new RequestHandler(metrics, "request-handler");
    Histogram responses = metrics.histogram(MetricRegistry.name(RequestHandler.class, "responses"));
    //        requestHandler.handleRequest(2);
    requestHandler.handleTimerRequest();
  }

  private static void counterMetrics() {
    //    QueueManager jobs = new QueueManager(metrics, "jobs");
    Counter counter = metrics.counter(MetricRegistry.name(QueueManager.class, "pending-jobs"));
    //    jobs.addJob("job1");
    //    wait5Seconds();
    //    jobs.takeJob();
  }

  private static void meterMetrics() {
    Meter requests = metrics.meter("requests");
    requests.mark(4L);
  }

  private static void healthChecks() {
    healthCheckRegistry.register("mongodb", new DatabaseHealthCheck(false));
    SortedMap<String, HealthCheck.Result> results = healthCheckRegistry.runHealthChecks();
    for (Map.Entry<String, HealthCheck.Result> entry : results.entrySet()) {
      if (entry.getValue().isHealthy()) {
        System.out.println(entry.getKey() + " is healthy");
      } else {
        System.err.println(entry.getKey() + " is UNHEALTHY: " + entry.getValue().getMessage());
        final Throwable e = entry.getValue().getError();
        if (e != null) {
          e.printStackTrace();
        }
      }
    }
  }

  static void startReport() {
    ConsoleReporter reporter =
        ConsoleReporter.forRegistry(metrics)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();
    reporter.start(1, TimeUnit.SECONDS);
  }

  static void wait5Seconds() {
    try {
      Thread.sleep(5 * 1000);
    } catch (InterruptedException e) {
    }
  }
}
