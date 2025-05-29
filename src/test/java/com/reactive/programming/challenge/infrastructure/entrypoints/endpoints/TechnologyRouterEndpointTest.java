package com.reactive.programming.challenge.infrastructure.entrypoints.endpoints;

import com.reactive.programming.challenge.domain.enums.TechnicalMessage;
import com.reactive.programming.challenge.domain.model.Person;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TechnologyRouterEndpointTest {
    @Autowired
    private WebTestClient webTestClient;

    private Person personSaved;

    @Test
    @Order(0)
    public void testEnrollPerson() {
        webTestClient.post()
                .uri("/users/api/person/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(List.of(8L, 25L)))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(body -> assertEquals(TechnicalMessage.PERSON_ENROLLED.getMessage(), body));
    }

}