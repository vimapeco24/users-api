package com.reactive.programming.challenge.application.config;

import com.reactive.programming.challenge.domain.api.IPersonServicePort;
import com.reactive.programming.challenge.domain.spi.IPersonBootcampPersistencePort;
import com.reactive.programming.challenge.domain.spi.IPersonPersistencePort;
import com.reactive.programming.challenge.domain.spi.IWebClientPersistencePort;
import com.reactive.programming.challenge.domain.usecase.PersonUseCase;
import com.reactive.programming.challenge.infrastructure.entrypoints.BootcampClient;
import com.reactive.programming.challenge.infrastructure.adapter.PersonAdapter;
import com.reactive.programming.challenge.infrastructure.adapter.PersonBootcampAdapter;
import com.reactive.programming.challenge.infrastructure.adapter.WebClientAdapter;
import com.reactive.programming.challenge.infrastructure.adapter.mapper.PersonBootcampEntityMapper;
import com.reactive.programming.challenge.infrastructure.adapter.mapper.PersonEntityMapper;
import com.reactive.programming.challenge.infrastructure.adapter.repository.PersonBootcampRepository;
import com.reactive.programming.challenge.infrastructure.adapter.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final PersonRepository personRepository;
    private final PersonBootcampRepository personBootcampRepository;
    private final PersonEntityMapper personEntityMapper;
    private final PersonBootcampEntityMapper personBootcampEntityMapper;
    private final BootcampClient bootcampClient;

    @Bean
    public IWebClientPersistencePort webClientPersistencePort() {
        return new WebClientAdapter(bootcampClient);
    }

    @Bean
    public IPersonPersistencePort personPersistencePort() {
        return new PersonAdapter(personRepository, personEntityMapper);
    }

    @Bean
    public IPersonBootcampPersistencePort personBootcampPersistencePort() {
        return new PersonBootcampAdapter(personBootcampRepository, personBootcampEntityMapper);
    }

    @Bean
    public IPersonServicePort personServicePort(IPersonPersistencePort personPersistencePort, IPersonBootcampPersistencePort personBootcampPersistencePort, IWebClientPersistencePort webClientPersistencePort) {
        return new PersonUseCase(personPersistencePort, personBootcampPersistencePort, webClientPersistencePort);
    }
}
