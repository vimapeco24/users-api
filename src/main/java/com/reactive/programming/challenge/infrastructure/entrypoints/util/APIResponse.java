package com.reactive.programming.challenge.infrastructure.entrypoints.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reactive.programming.challenge.infrastructure.entrypoints.dto.PersonDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class APIResponse {
    private String code;
    private String message;
    private String identifier;
    private String date;
    private PersonDTO data;
    private List<ErrorDTO> errors;
}
