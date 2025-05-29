package com.reactive.programming.challenge.infrastructure.entrypoints;

import com.reactive.programming.challenge.domain.model.Bootcamp;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BootcampClient {
    private final WebClient webClient;

    public BootcampClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8082").build();
    }

    public Mono<Bootcamp> getBootcamp(Long id) {
        return webClient.get()
                .uri("/bootcamps/api/bootcamp/{id}", id)
                .retrieve()
                .bodyToMono(Bootcamp.class);
    }
}
