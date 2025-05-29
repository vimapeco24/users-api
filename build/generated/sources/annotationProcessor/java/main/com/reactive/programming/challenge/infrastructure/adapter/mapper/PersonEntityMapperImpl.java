package com.reactive.programming.challenge.infrastructure.adapter.mapper;

import com.reactive.programming.challenge.domain.model.Person;
import com.reactive.programming.challenge.infrastructure.adapter.entity.PersonEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T20:42:13-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class PersonEntityMapperImpl implements PersonEntityMapper {

    @Override
    public Person toPerson(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( personEntity.getId() );
        person.setName( personEntity.getName() );
        person.setEmail( personEntity.getEmail() );

        return person;
    }

    @Override
    public PersonEntity toEntity(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setId( person.getId() );
        personEntity.setName( person.getName() );
        personEntity.setEmail( person.getEmail() );

        return personEntity;
    }
}
