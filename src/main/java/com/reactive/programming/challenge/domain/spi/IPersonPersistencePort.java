package com.reactive.programming.challenge.domain.spi;

import com.reactive.programming.challenge.domain.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IPersonPersistencePort {
    Mono<Person> createPerson(Person person);
    Mono<Person> getPerson(Long id);
    Mono<Void> deleteCapacity(Long id);
    Flux<Person> findByIdIn(List<Long> ids);
    Mono<Boolean> existsByName(String name);
}
