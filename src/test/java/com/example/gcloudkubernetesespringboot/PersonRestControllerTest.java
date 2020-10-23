package com.example.gcloudkubernetesespringboot;

import com.example.gcloudkubernetesespringboot.model.entity.Person;
import com.example.gcloudkubernetesespringboot.model.repo.PersonRepository;
import com.example.gcloudkubernetesespringboot.service.PersonService;
import com.example.gcloudkubernetesespringboot.web.PersonRestController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@Import({PersonService.class, PersonRestController.class})
public class PersonRestControllerTest {

  @Autowired private WebTestClient webTestClient;
  @MockBean private PersonRepository repository;

  @Test
  public void createPerson() {
    Person person = Person.builder().firstname("mozamaml").lastname("hossain").build();
    Mockito.when(repository.save(Mockito.any(Person.class))).thenReturn(Mono.just(person));
    webTestClient
        .post()
        .uri("/persons")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(person), Person.class)
        .exchange()
        .expectStatus()
        .isCreated();
  }
}
