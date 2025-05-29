package com.reactive.programming.challenge.infrastructure.adapter.repository;

import com.reactive.programming.challenge.infrastructure.adapter.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PersonRepository extends ReactiveCrudRepository<PersonEntity, Long> {
    Mono<PersonEntity> findByName(String name);
    Flux<PersonEntity> findByIdIn(List<Long> ids);

}
