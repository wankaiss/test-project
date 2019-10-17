package com.citi.time.test;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class MapTest {
  @Test
  public void testMap() {
    HashMap<Integer, List<String>> integerListHashMap = new HashMap<>();
    integerListHashMap.put(15, asList("151", "152"));
    integerListHashMap.put(16, asList("161", "162"));
    integerListHashMap.put(17, asList("171", "172"));

    Integer first = integerListHashMap.keySet().stream().max(Comparator.comparingInt(Integer::intValue)).get();
    List<String> strings3 =
        integerListHashMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
    System.out.println(strings3);
  }
}
