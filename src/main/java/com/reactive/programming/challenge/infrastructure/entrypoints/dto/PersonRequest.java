package com.reactive.programming.challenge.infrastructure.entrypoints.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class PersonRequest {
    private Long id;
    private String name;
    private String email;
    List<Long> bootcampId;
}
