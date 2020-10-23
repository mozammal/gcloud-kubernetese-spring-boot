package com.example.gcloudkubernetesespringboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ActuatorRestController {

  @GetMapping("/")
  public Flux<String> getRoot() {
    return Flux.just("still alive for you");
  }
}
