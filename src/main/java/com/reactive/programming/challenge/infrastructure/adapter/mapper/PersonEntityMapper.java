package com.reactive.programming.challenge.infrastructure.adapter.mapper;

import com.reactive.programming.challenge.domain.model.Person;
import com.reactive.programming.challenge.infrastructure.adapter.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonEntityMapper {
    Person toPerson(PersonEntity personEntity);
    PersonEntity toEntity(Person person);
}
