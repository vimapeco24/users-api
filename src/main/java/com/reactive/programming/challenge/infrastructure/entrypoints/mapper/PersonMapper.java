package com.reactive.programming.challenge.infrastructure.entrypoints.mapper;

import com.reactive.programming.challenge.infrastructure.entrypoints.dto.PersonRequest;
import com.reactive.programming.challenge.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    Person personRequestToPerson(PersonRequest personRequest);}
