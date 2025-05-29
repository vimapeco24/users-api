package com.reactive.programming.challenge.infrastructure.entrypoints.handler.impl;

import com.reactive.programming.challenge.infrastructure.entrypoints.dto.PersonRequest;
import com.reactive.programming.challenge.infrastructure.entrypoints.handler.IPersonHandler;
import com.reactive.programming.challenge.infrastructure.entrypoints.mapper.PersonMapper;
import com.reactive.programming.challenge.infrastructure.entrypoints.util.APIResponse;
import com.reactive.programming.challenge.infrastructure.entrypoints.util.Constants;
import com.reactive.programming.challenge.domain.api.IPersonServicePort;
import com.reactive.programming.challenge.domain.enums.TechnicalMessage;
import com.reactive.programming.challenge.infrastructure.entrypoints.util.ErrorDTO;
import com.reactive.programming.challenge.domain.exceptions.BusinessException;
import com.reactive.programming.challenge.domain.exceptions.TechnicalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.reactive.programming.challenge.infrastructure.entrypoints.util.Constants.TECHNOLOGY_ERROR;
import static com.reactive.programming.challenge.infrastructure.entrypoints.util.Constants.X_MESSAGE_ID;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonHandler implements IPersonHandler {
    private final IPersonServicePort personServicePort;
    private final PersonMapper personMapper;

    @Override
    public Mono<ServerResponse> createPerson(ServerRequest request) {
        String messageId = getMessageId(request);
        log.info(messageId);
        return request.bodyToMono(PersonRequest.class)
                .flatMap(personRequest -> personServicePort.registerPerson(personMapper.personRequestToPerson(personRequest))
                        .doOnSuccess(technologySaved -> log.info("{}: {}", Constants.TECHNOLOGY_CREATED, messageId))
                )
                .flatMap(technologySaved -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .bodyValue(TechnicalMessage.USER_CREATED.getMessage()))
                .contextWrite(Context.of(X_MESSAGE_ID, messageId))
                .doOnError(ex -> log.error(TECHNOLOGY_ERROR, ex))
                .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        messageId,
                        TechnicalMessage.INVALID_PARAMETERS,
                        List.of(ErrorDTO.builder()
                                        .code(ex.getTechnicalMessage().getCode())
                                        .message(ex.getTechnicalMessage().getMessage())
                                        .param(ex.getTechnicalMessage().getParam())
                                .build())
                ))
                .onErrorResume(TechnicalException.class, ex -> buildErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        messageId,
                        TechnicalMessage.INTERNAL_ERROR,
                        List.of(ErrorDTO.builder()
                                .code(ex.getTechnicalMessage().getCode())
                                .message(ex.getTechnicalMessage().getMessage())
                                .param(ex.getTechnicalMessage().getParam())
                                .build())
                ))
                .onErrorResume(ex -> {
                    log.error("{}: {}", Constants.UNEXPECTED_ERROR, messageId, ex);
                    return buildErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            messageId,
                            TechnicalMessage.INTERNAL_ERROR,
                            List.of(ErrorDTO.builder()
                                            .code(TechnicalMessage.INTERNAL_ERROR.getCode())
                                            .message(TechnicalMessage.INTERNAL_ERROR.getMessage())
                                    .build())
                    );
                });
    }

    @Override
    public Mono<ServerResponse> getPerson(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return personServicePort.getPerson(id)
                .flatMap(person -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(fromValue(person)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> deleteTechnology(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<Void> technologyDeleted = personServicePort.deleteCapacity(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(technologyDeleted, Void.class);
    }

    @Override
    public Mono<ServerResponse> enrollInABootcamp(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                .flatMap(bootcampIds -> personServicePort.enrollInABootcamp(id, bootcampIds)
                        .flatMap(personEnrolled -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(TechnicalMessage.PERSON_ENROLLED.getMessage())
                        )
                );
    }

    @Override
    public Mono<ServerResponse> getTechnologiesByIdIn(ServerRequest request) {
        return request.bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                .flatMap(ids -> personServicePort.getTechnologiesByIdIn(ids)
                        .collectList()
                        .flatMap(technologies -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(technologies)
                        )
                );
    }

    @Override
    public Mono<ServerResponse> buildErrorResponse(HttpStatus httpStatus, String identifier, TechnicalMessage error, List<ErrorDTO> errors) {
        return Mono.defer(() -> {
            APIResponse apiErrorResponse = APIResponse
                    .builder()
                    .code(error.getCode())
                    .message(error.getMessage())
                    .identifier(identifier)
                    .date(Instant.now().toString())
                    .errors(errors)
                    .build();
            return ServerResponse.status(httpStatus)
                    .bodyValue(apiErrorResponse);
        });
    }

    private String getMessageId(ServerRequest serverRequest) {
        return serverRequest.headers()
                .firstHeader(X_MESSAGE_ID) != null
                ? serverRequest.headers().firstHeader(X_MESSAGE_ID)
                : UUID.randomUUID().toString();
    }
}
