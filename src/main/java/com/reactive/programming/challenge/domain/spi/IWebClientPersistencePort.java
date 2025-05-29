package com.reactive.programming.challenge.domain.spi;

import com.reactive.programming.challenge.domain.model.Bootcamp;
import reactor.core.publisher.Mono;

public interface IWebClientPersistencePort {
    Mono<Bootcamp> getBootcamp(Long id);
}
