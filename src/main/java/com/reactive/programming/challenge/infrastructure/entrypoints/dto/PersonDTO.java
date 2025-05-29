package com.reactive.programming.challenge.infrastructure.entrypoints.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class PersonDTO {
    private Long id;
    private String name;
    private String email;
    private List<Long> bootcampId;

}
