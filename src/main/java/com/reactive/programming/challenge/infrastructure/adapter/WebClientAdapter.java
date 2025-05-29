package com.reactive.programming.challenge.infrastructure.adapter;

import com.reactive.programming.challenge.domain.model.Bootcamp;
import com.reactive.programming.challenge.domain.spi.IWebClientPersistencePort;
import com.reactive.programming.challenge.infrastructure.entrypoints.BootcampClient;
import reactor.core.publisher.Mono;

public class WebClientAdapter implements IWebClientPersistencePort {
    private final BootcampClient bootcampClient;

    public WebClientAdapter(BootcampClient bootcampClient) {
        this.bootcampClient = bootcampClient;
    }

    @Override
    public Mono<Bootcamp> getBootcamp(Long id) {
        return bootcampClient.getBootcamp(id);
    }
}
