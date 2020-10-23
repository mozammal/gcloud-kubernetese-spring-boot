package com.example.gcloudkubernetesespringboot.model.repo;

import com.example.gcloudkubernetesespringboot.model.entity.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

  Flux<Person> findByFirstname(String firstname);

  Flux<Person> findByLastname(String lastname);

  Flux<Person> findByFirstnameAndLastname(String firstname, String lastname);
}
