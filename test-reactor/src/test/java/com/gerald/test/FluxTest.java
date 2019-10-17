package com.gerald.test;


import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierExtensionsKt;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class FluxTest {

  @Test
  public void fluxTest() {
//    Mono<List<String>> just = Flux.just("one", "two", "three").collect(Collectors.toList());
    Mono<List<String>> just = Flux.just("one", "two", "three")
//    Mono<List<List<String>>> just = Flux.just(asList("one"), asList("two"), asList("three"))
//            .flatMap(first -> Mono.just(first.get(0) + "plus1"))
            .flatMap(first -> Mono.just("first"))
            .flatMap(second -> Mono.just("second") )
            .concatMap(third -> Mono.just("third"))
            .collectList();

    StepVerifier.create(just).expectNext(asList("one", "two", "three")).verifyComplete();
  }

  @Test
  public void monoTest() {
    Mono<String> firstMono = Mono.just("oneMono");//.flatMap(one -> Mono.just("first"));
    Mono<String> secondMono = Mono.just("twoMono");//.flatMap(one -> Mono.just("first"));
    Mono<String> thirdMono = Mono.just("threeMono");//.flatMap(one -> Mono.just("first"));
    Mono<String> fourthMono = Mono.just("fourMono");//.flatMap(one -> Mono.just("first"));

      Flux<String> stringFlux = Flux.just("one", "two", "three", "four").flatMap(fluxKey -> {
          if (fluxKey.equals("one")) {
              return firstMono;
          } else if (fluxKey.equals("two")) {
              return secondMono;
          } else if (fluxKey.equals("three")) {
              return thirdMono;
          } else if (fluxKey.equals("four")) {
              return fourthMono;
          }
          return null;
      });

      StepVerifier.create(stringFlux).expectNext("oneMono", "twoMono", "threeMono", "fourMono").verifyComplete();
  }
}
