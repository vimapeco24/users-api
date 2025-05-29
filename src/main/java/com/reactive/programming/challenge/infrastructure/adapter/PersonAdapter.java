package com.reactive.programming.challenge.infrastructure.adapter;

import com.reactive.programming.challenge.domain.model.Person;
import com.reactive.programming.challenge.domain.spi.IPersonPersistencePort;
import com.reactive.programming.challenge.infrastructure.adapter.mapper.PersonEntityMapper;
import com.reactive.programming.challenge.infrastructure.adapter.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class PersonAdapter implements IPersonPersistencePort {
    private final PersonRepository personRepository;
    private final PersonEntityMapper personEntityMapper;

    @Override
    public Mono<Person> createPerson(Person person) {
        return personRepository.save(personEntityMapper.toEntity(person))
                .map(personEntityMapper::toPerson);
    }

    @Override
    public Mono<Person> getPerson(Long id) {
        log.info("getPerson, ID: {}", id);
        return personRepository.findById(id)
                .map(personEntityMapper::toPerson);
    }

    @Override
    public Mono<Void> deleteCapacity(Long id) {
        return personRepository.deleteById(id);
    }

    @Override
    public Flux<Person> findByIdIn(List<Long> ids) {
        return personRepository.findByIdIn(ids)
                .map(personEntityMapper::toPerson);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        log.info("existsByName, name: {}", name);
        return personRepository.findByName(name)
                .map(personEntityMapper::toPerson)
                .map(technologyEntity -> true)
                .defaultIfEmpty(false);
    }
}
