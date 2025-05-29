package com.reactive.programming.challenge.domain.spi;

import com.reactive.programming.challenge.domain.model.PersonBootcamp;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IPersonBootcampPersistencePort {
    Flux<PersonBootcamp> createAll(List<PersonBootcamp> relations);
}
