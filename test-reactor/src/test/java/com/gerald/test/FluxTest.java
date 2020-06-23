package com.gerald.test;

import static java.util.Arrays.asList;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxTest {

  @Test
  public void fluxTest() {
    //    Mono<List<String>> just = Flux.just("one", "two", "three").collect(Collectors.toList());
    Mono<List<String>> just =
        Flux.just("one", "two", "three")
            //    Mono<List<List<String>>> just = Flux.just(asList("one"), asList("two"),
            // asList("three"))
            //            .flatMap(first -> Mono.just(first.get(0) + "plus1"))
            .flatMap(first -> Mono.just("first"))
            .flatMap(second -> Mono.just("second"))
            .concatMap(third -> Mono.just("third"))
            .collectList();

    StepVerifier.create(just).expectNext(asList("one", "two", "three")).verifyComplete();
  }

  @Test
  public void monoTest() {
    Mono<String> firstMono = Mono.just("oneMono"); // .flatMap(one -> Mono.just("first"));
    Mono<String> secondMono = Mono.just("twoMono"); // .flatMap(one -> Mono.just("first"));
    Mono<String> thirdMono = Mono.just("threeMono"); // .flatMap(one -> Mono.just("first"));
    Mono<String> fourthMono = Mono.just("fourMono"); // .flatMap(one -> Mono.just("first"));

    Flux<String> stringFlux =
        Flux.just("one", "two", "three", "four")
            .flatMap(
                fluxKey -> {
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

    StepVerifier.create(stringFlux)
        .expectNext("oneMono", "twoMono", "threeMono", "fourMono")
        .verifyComplete();
  }

  @Test
  public void testFluxWithObject() {
    Flux<SimpleObject> fluxJust =
        Flux.just(
            new SimpleObject(15, asList("foo1", "foo2")),
            new SimpleObject(30, asList("bar1", "bar2")),
            new SimpleObject(54, asList("car1", "car2")));
    Mono<Map<Integer, List<String>>> mapMono =
        fluxJust
            .collectMap(SimpleObject::getCount, SimpleObject::getItems, HashMap::new)
            .map(
                map -> {
                  Map<Integer, List<String>> res = new HashMap<>();
                  Integer maxKey =
                      map.keySet().stream().max((Comparator.comparingInt(Integer::intValue))).get();
                  List<String> value =
                      map.values().stream().flatMap(List::stream).collect(Collectors.toList());
                  res.put(maxKey, value);
                  return res;
                })
            .doOnNext(System.out::println);

    //    Flux<String> flux = mapMono.flux().flatMap(map ->
    // Flux.fromIterable(map.values())).flatMap(list -> Flux.fromIterable(list));

    StepVerifier.create(mapMono)
        .expectNext(
            (Map<Integer, List<String>>)
                new HashMap<Integer, List<String>>().put(20, asList("test")))
        .verifyComplete();
  }

  class ResultFunction implements Function<Map<Integer, List<String>>, Map<Integer, List<String>>> {

    @Override
    public Map<Integer, List<String>> apply(Map<Integer, List<String>> integerListMap) {
      //      integerListMap.entrySet().stream().sorted(Comparator.comparingInt(HashMap::keySet))
      return null;
    }
  }

  @Data
  @AllArgsConstructor
  class SimpleObject {
    private int count;
    private List<String> items;
  }
}
