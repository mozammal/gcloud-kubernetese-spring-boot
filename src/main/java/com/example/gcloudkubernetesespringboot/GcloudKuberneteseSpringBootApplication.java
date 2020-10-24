package com.example.gcloudkubernetesespringboot;

import com.example.gcloudkubernetesespringboot.model.entity.Person;
import com.example.gcloudkubernetesespringboot.model.repo.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class GcloudKuberneteseSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(GcloudKuberneteseSpringBootApplication.class, args);
  }
}
