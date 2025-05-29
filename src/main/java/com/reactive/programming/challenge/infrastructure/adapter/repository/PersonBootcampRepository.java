package com.reactive.programming.challenge.infrastructure.adapter.repository;

import com.reactive.programming.challenge.domain.model.PersonBootcamp;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonBootcampRepository extends ReactiveCrudRepository<PersonBootcamp, Long> {
}
