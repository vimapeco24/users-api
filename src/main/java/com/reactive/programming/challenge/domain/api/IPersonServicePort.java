package com.reactive.programming.challenge.domain.api;

import com.reactive.programming.challenge.domain.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IPersonServicePort {
    Mono<Person> registerPerson(Person person);
    Mono<Person> getPerson(Long id);
    Mono<Person> enrollInABootcamp(Long id, List<Long> bootcampIds);
    Mono<Void> deleteCapacity(Long id);
    Flux<Person> getTechnologiesByIdIn(List<Long> ids);
}
