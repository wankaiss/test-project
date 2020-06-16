package com.test.techtest;

import org.springframework.stereotype.Service;

@Service
public class GoodbyeService {
  public String sayGoodbye(String goodbye) {
    return "Speak " + goodbye;
  }
}
