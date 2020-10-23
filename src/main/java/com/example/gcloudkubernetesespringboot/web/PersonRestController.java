package com.example.gcloudkubernetesespringboot.web;

import com.example.gcloudkubernetesespringboot.model.entity.Person;

import com.example.gcloudkubernetesespringboot.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/persons")
public class PersonRestController {

  private final PersonService personService;

  public PersonRestController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Person> createPerson(@RequestBody Person person) {
    return personService.create(person);
  }

  @GetMapping("/{id}")
  Mono<Person> findPersonById(@PathVariable("id") String id) {
    return personService.findPersonById(id);
  }

  @GetMapping
  public Flux<Person> findAllCustomers() {
    return personService.findAllPerson();
  }

  @PutMapping("/{id}")
  public Mono<Person> updatePersonById(@PathVariable String id, @RequestBody Person person) {
    return personService.updatePersonById(id, person);
  }

  @DeleteMapping("/{id}")
  void deletePersonById(@PathVariable String id) {
    personService.deletePersonById(id);
  }
}
