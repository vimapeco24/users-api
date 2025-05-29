package com.reactive.programming.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String email;
    List<Long> bootcampId;

    public Person(String name, String email, List<Long> bootcampId) {
        this.name = name;
        this.email = email;
        this.bootcampId = bootcampId;
    }
}
