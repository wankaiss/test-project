package com.citi.time.test;

import static java.util.Arrays.asList;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class InstantTest {
  @Test
  public void test1() {
    Instant now = Instant.now(Clock.systemDefaultZone());
    LocalDate localDate = LocalDate.now();
    System.out.print(localDate + "\n");
    System.out.print(now.toString());
  }

  @Test
  public void testDateTimeFormatter() {
    List<LocalDate> valuateDates =
        asList(LocalDate.parse("2019-08-07"), LocalDate.parse("2019-08-01"));

    List<String> collect =
        valuateDates.stream()
            .map(t -> t.format(DateTimeFormatter.ISO_DATE))
            .collect(Collectors.toList());

    for (LocalDate valuateDate : valuateDates) {
      System.out.println(valuateDate.format(DateTimeFormatter.ISO_DATE));
    }

    for (String s : collect) {
      System.out.println(s);
    }
  }
}
