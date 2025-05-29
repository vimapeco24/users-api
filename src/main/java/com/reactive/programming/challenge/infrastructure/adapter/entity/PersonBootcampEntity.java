package com.reactive.programming.challenge.infrastructure.adapter.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "person_bootcamp")
@Getter
@Setter
@RequiredArgsConstructor
public class PersonBootcampEntity {
    @Id
    private Long id;
    @Column("person_id")
    private Long personId;
    @Column("bootcamp_id")
    private Long bootcampId;
}
