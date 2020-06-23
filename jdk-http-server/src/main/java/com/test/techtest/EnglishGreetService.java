package com.test.techtest;

import org.springframework.stereotype.Component;

@Component
public class EnglishGreetService implements GreetService {

  @Override
  public String sayHello(String hello) {
    return "English Hello";
  }
}
