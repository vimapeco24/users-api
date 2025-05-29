package com.reactive.programming.challenge.infrastructure.adapter;

import com.reactive.programming.challenge.domain.model.PersonBootcamp;
import com.reactive.programming.challenge.domain.spi.IPersonBootcampPersistencePort;
import com.reactive.programming.challenge.infrastructure.adapter.mapper.PersonBootcampEntityMapper;
import com.reactive.programming.challenge.infrastructure.adapter.repository.PersonBootcampRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.List;

@AllArgsConstructor
public class PersonBootcampAdapter implements IPersonBootcampPersistencePort {
    private final PersonBootcampRepository personBootcampRepository;
    private final PersonBootcampEntityMapper personBootcampEntityMapper;

    @Override
    public Flux<PersonBootcamp> createAll(List<PersonBootcamp> relations) {
        return personBootcampRepository.saveAll(relations);
    }
}
