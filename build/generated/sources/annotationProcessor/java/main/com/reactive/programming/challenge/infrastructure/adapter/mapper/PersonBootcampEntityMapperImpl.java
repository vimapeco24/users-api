package com.reactive.programming.challenge.infrastructure.adapter.mapper;

import com.reactive.programming.challenge.domain.model.PersonBootcamp;
import com.reactive.programming.challenge.infrastructure.adapter.entity.PersonBootcampEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T20:42:13-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class PersonBootcampEntityMapperImpl implements PersonBootcampEntityMapper {

    @Override
    public PersonBootcamp toPersonBootcamp(PersonBootcampEntity personBootcampEntity) {
        if ( personBootcampEntity == null ) {
            return null;
        }

        PersonBootcamp personBootcamp = new PersonBootcamp();

        personBootcamp.setId( personBootcampEntity.getId() );
        personBootcamp.setPersonId( personBootcampEntity.getPersonId() );
        personBootcamp.setBootcampId( personBootcampEntity.getBootcampId() );

        return personBootcamp;
    }

    @Override
    public PersonBootcampEntity toEntity(PersonBootcamp personBootcamp) {
        if ( personBootcamp == null ) {
            return null;
        }

        PersonBootcampEntity personBootcampEntity = new PersonBootcampEntity();

        personBootcampEntity.setId( personBootcamp.getId() );
        personBootcampEntity.setPersonId( personBootcamp.getPersonId() );
        personBootcampEntity.setBootcampId( personBootcamp.getBootcampId() );

        return personBootcampEntity;
    }
}
