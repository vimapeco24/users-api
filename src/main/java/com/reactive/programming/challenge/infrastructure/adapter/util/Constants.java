package com.reactive.programming.challenge.infrastructure.adapter.util;

public class Constants {
    public static final String API_PERSON = "/users/api/person";
    public static final String PATH_POST_PERSON = "/users/api/person";
    public static final String PATH_GET_PERSON = "/users/api/person/{id}";
    public static final String PATH_ENROLL_USER = "/users/api/person/{id}";
    public static final String USER_CREATED = "User created successfully";
    public static final String STRING = "string";
    public static final String OK = "200";
    public static final String BAD_REQUEST_CODE = "400";
    public static final String BAD_REQUEST_DESCRIPTION = "Bad Request, please verify data";
    public static final String OBJECT = "object";
    public static final String REQUEST_BODY_DESCRIPTION = "Object representing the person to be created";
    public static final String USERS = "Users";
    public static final String OPERATION_SUMMARY = "Create a new user";
    public static final String METHOD_NAME = "enrollInABootcamp";
    public static final String INFO_TITLE = "User API";
    public static final String INFO_VERSION = "1.0";
    public static final String INFO_DESCRIPTION = "API para la gestión de personas en programación reactiva";
    public static final String ERROR_RESPONSE = """
                {
                    "code": "400",
                    "message": "Bad Parameters, please verify data",
                    "identifier": "bf9e83ca-3fb6-4f54-9913-795ac0dd1241",
                    "date": "2025-04-29T17:01:30.150022600Z",
                    "errors": [
                        {
                            "code": "400",
                            "message": "Person name must be less than 100 characters"
                        }
                    ]
                }
            """;
}
