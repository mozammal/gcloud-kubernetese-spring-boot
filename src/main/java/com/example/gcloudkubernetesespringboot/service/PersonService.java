package com.example.gcloudkubernetesespringboot.service;

import com.example.gcloudkubernetesespringboot.model.entity.Person;
import com.example.gcloudkubernetesespringboot.model.repo.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Mono<Person> create(Person person) {
    log.info("creating new user {}", person);
    return personRepository.save(person);
  }

  public Flux<Person> findAllPerson() {
    log.info("findding all users");
    return personRepository.findAll();
  }

  public Mono<Person> findPersonById(String id) {
    return personRepository.findById(id);
  }

  public Mono<Person> updatePersonById(String id, Person person) {
    return personRepository
        .findById(id)
        .map(
            p ->
                Person.builder()
                    .firstname(person.getFirstname())
                    .lastname(person.getLastname())
                    .id(p.getId())
                    .build())
        .flatMap(personRepository::save);
  }

  public void deletePersonById(String id) {
    personRepository.findById(id).flatMap(p -> personRepository.delete(p)).subscribe();
  }
}
