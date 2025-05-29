package com.reactive.programming.challenge.infrastructure.entrypoints;

import com.reactive.programming.challenge.infrastructure.entrypoints.handler.IPersonHandler;
import com.reactive.programming.challenge.domain.model.Person;
import com.reactive.programming.challenge.infrastructure.adapter.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class PersonRouterEndpoint {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = Constants.API_PERSON,
                    produces = { MediaType.APPLICATION_JSON_VALUE },
                    method = RequestMethod.POST,
                    beanClass = IPersonHandler.class,
                    beanMethod = Constants.METHOD_NAME,
                    operation = @Operation(
                            operationId = Constants.METHOD_NAME,
                            summary = Constants.OPERATION_SUMMARY,
                            tags = { Constants.USERS},
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    description = Constants.REQUEST_BODY_DESCRIPTION,
                                    required = true,
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Person.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = Constants.OK,
                                            description = Constants.USER_CREATED,
                                            content = @Content(
                                                    mediaType = MediaType.TEXT_PLAIN_VALUE,
                                                    schema = @Schema(type = Constants.STRING, example = Constants.USER_CREATED)
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = Constants.BAD_REQUEST_CODE,
                                            description = Constants.BAD_REQUEST_DESCRIPTION,
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(type = Constants.OBJECT, example = Constants.ERROR_RESPONSE)
                                            )
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> personRouter(IPersonHandler personHandler) {
        return RouterFunctions
                .route(POST(Constants.PATH_POST_PERSON), personHandler::createPerson)
                .andRoute(POST(Constants.PATH_ENROLL_USER), personHandler::enrollInABootcamp);
//                .andRoute(POST(Constants.PATH_GET_USERS_BY_ID_IN), technologyHandler::getTechnologiesByIdIn);
    }
}
