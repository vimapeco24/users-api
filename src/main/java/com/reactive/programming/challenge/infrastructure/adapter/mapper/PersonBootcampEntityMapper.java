package com.reactive.programming.challenge.infrastructure.adapter.mapper;

import com.reactive.programming.challenge.domain.model.PersonBootcamp;
import com.reactive.programming.challenge.infrastructure.adapter.entity.PersonBootcampEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonBootcampEntityMapper {
    PersonBootcamp toPersonBootcamp(PersonBootcampEntity personBootcampEntity);
    PersonBootcampEntity toEntity(PersonBootcamp personBootcamp);
}
