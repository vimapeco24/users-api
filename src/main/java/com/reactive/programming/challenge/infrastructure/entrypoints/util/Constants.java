package com.reactive.programming.challenge.infrastructure.entrypoints.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String X_MESSAGE_ID = "x-message-id";
    public static final String TECHNOLOGY_ERROR = "Error on Person - [ERROR]";
    public static final String UNEXPECTED_ERROR = "Unexpected error occurred while creating person for messageId";
    public static final String TECHNOLOGY_CREATED = "Person created successfully with messageId";
}
