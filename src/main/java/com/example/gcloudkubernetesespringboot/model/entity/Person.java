package com.example.gcloudkubernetesespringboot.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
public class Person {

  @Id private String id;
  private String firstname;
  private String lastname;
}
