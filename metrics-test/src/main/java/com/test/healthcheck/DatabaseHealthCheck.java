package com.test.healthcheck;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {

  private boolean b = false;

  public DatabaseHealthCheck(boolean b) {
    this.b = b;
  }

  @Override
  protected Result check() throws Exception {
    if (b) {
      return HealthCheck.Result.healthy();
    } else {
      return HealthCheck.Result.unhealthy("Mongodb disconnected");
    }
  }
}
