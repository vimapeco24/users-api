package com.reactive.programming.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonBootcamp {
    private Long id;
    private Long personId;
    private Long bootcampId;

    public PersonBootcamp(Long personId, Long bootcampId) {
        this.personId = personId;
        this.bootcampId = bootcampId;
    }
}
