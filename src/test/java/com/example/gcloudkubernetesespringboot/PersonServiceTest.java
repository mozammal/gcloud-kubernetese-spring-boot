package com.example.gcloudkubernetesespringboot;

import com.example.gcloudkubernetesespringboot.model.entity.Person;
import com.example.gcloudkubernetesespringboot.model.repo.PersonRepository;
import com.example.gcloudkubernetesespringboot.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;
import java.util.function.Predicate;

@Slf4j
@DataMongoTest
@Import(PersonService.class)
public class PersonServiceTest {

  private final PersonService service;
  private final PersonRepository repository;

  public PersonServiceTest(@Autowired PersonService service,@Autowired PersonRepository repository) {
    this.service = service;
    this.repository = repository;
  }

  @Test
  public void create() {
    Mono<Person> person =
        service.create(Person.builder().firstname("asif").lastname("hossain").build());
    StepVerifier.create(person)
        .expectNextMatches(person1 -> StringUtils.hasText(person1.getId()))
        .verifyComplete();
  }
    @Test
    public void findAllPerson() {
        Flux<Person> savedPerson =
                repository.saveAll(
                        Flux.just(Person.builder().firstname("mozammal2").lastname("hossain2").build()));
        Flux<Person> persons = service.findAllPerson().thenMany(savedPerson);
        Predicate<Person> predicate =
                person ->
                        savedPerson.any(alalreadySavedPerson -> alalreadySavedPerson.equals(person)).block();
        StepVerifier.create(persons).expectNextMatches(predicate).verifyComplete();
    }

  @Test
  public void findById() {
    String id = UUID.randomUUID().toString();
    log.info(id);
    Mono<Person> personMono =
        service
            .create(Person.builder().firstname("asif1").lastname("hossain1").id(id).build())
            .flatMap(person -> service.findPersonById(person.getId()));
    StepVerifier.create(personMono)
        .expectNextMatches(p -> StringUtils.hasText(p.getId()) && id.equalsIgnoreCase(p.getId()))
        .verifyComplete();
  }
}
