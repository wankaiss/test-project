package com.citi.time.test;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;

public class InstantTest {
  @Test
  public void test1() {
    Instant now = Instant.now(Clock.systemDefaultZone());
    LocalDate localDate = LocalDate.now();
    System.out.print(localDate + "\n");
    System.out.print(now.toString());
  }
}
