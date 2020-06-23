package com.test.techtest;

import org.springframework.stereotype.Component;

@Component
public class ChineseGreetService implements GreetService {
  @Override
  public String sayHello(String hello) {
    return "Chinese " + hello;
  }
}
