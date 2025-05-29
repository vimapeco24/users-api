package com.reactive.programming.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Bootcamp {
    private Long id;
    private String name;
    private String description;
    private LocalDate launchDate;
    private Integer durationInWeeks;
    private List<Long> capacities;

    public Bootcamp(String name, String description, LocalDate launchDate, Integer durationInWeeks, List<Long> capacities) {
        this.name = name;
        this.description = description;
        this.launchDate = launchDate;
        this.durationInWeeks = durationInWeeks;
        this.capacities = capacities;
    }
}
