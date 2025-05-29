package com.reactive.programming.challenge.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TechnicalMessage {

    INTERNAL_ERROR("500", "Something went wrong, please try again", ""),
    INTERNAL_ERROR_IN_ADAPTERS("PRC501", "Something went wrong in adapters, please try again", ""),
    INVALID_REQUEST("400", "Bad Request, please verify data", ""),
    INVALID_PARAMETERS(INVALID_REQUEST.getCode(), "Bad Parameters, please verify data", ""),
    // INVALID_EMAIL("403", "Invalid email, please verify", "email"),
    INVALID_MESSAGE_ID("404", "Invalid Message ID, please verify", "messageId"),
    UNSUPPORTED_OPERATION("501", "Method not supported, please try again", ""),
    USER_CREATED("201", "User created successfully", ""),
    USER_NOT_FOUND("201", "The user with that id does not exist.", ""),
    BOOTCAMP_NOT_FOUND("201", "The bootcamp with that id does not exist.", ""),
    PERSON_ENROLLED("201", "Person enrolled successfully", ""),
    ADAPTER_RESPONSE_NOT_FOUND("404-0", "Invalid email, please verify", ""),
    USER_ALREADY_EXISTS("400", "The user with the name provided already exists.", ""),
    BOOTCAMPS_LIMIT("400", "The enroll must have a maximum of 5 bootcamps.", ""),
    BOOTCAMPS_DATE_CONFLICT("400", "There is a conflict between start dates.", ""),
    NOT_ONLY_NUMBERS("400", "User name cannot contain only numbers", ""),
    DESCRIPTION_CHARACTER_LIMIT("400", "User description must be less than 90 characters", ""),
    NAME_CHARACTER_LIMIT("400", "User name must be less than 50 characters", ""),
    MIN_NUMBER_CAPACITIES("400", "The bootcamp must have at least one capacity.", ""),
    MAX_NUMBER_CAPACITIES("400", "The bootcamp must have a maximum of 4 capacities.", ""),
    DUPLICATED_CAPACITIES("400", "bootcamp duplicated was found.", ""),
    CAPACITY_NOT_FOUND("404", "The bootcamp with that id does not exist.", "");




    private final String code;
    private final String message;
    private final String param;
}
