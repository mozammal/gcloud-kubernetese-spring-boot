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

  /*  @Bean
  ApplicationRunner loadData(PersonRepository personRepository) {

    return args ->
        personRepository
            .deleteAll()
            .thenMany(
                Flux.just(
                        new String[][] {
                          {"mozammal", "hossain"}, {"nasrin", "khatun"}, {"ayub", "rahman"}
                        })
                    .map(p -> Person.builder().firstname(p[0]).lastname(p[1]).build())
                    .flatMap(personRepository::save))
            .thenMany(personRepository.findAll())
            .subscribe(person -> log.info(person.toString()));
  }*/
}
