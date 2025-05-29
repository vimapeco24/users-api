package com.reactive.programming.challenge.infrastructure.entrypoints.mapper;

import com.reactive.programming.challenge.domain.model.Person;
import com.reactive.programming.challenge.infrastructure.entrypoints.dto.PersonRequest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T20:55:47-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person personRequestToPerson(PersonRequest personRequest) {
        if ( personRequest == null ) {
            return null;
        }

        Person person = new Person();

        person.setName( personRequest.getName() );
        person.setEmail( personRequest.getEmail() );
        List<Long> list = personRequest.getBootcampId();
        if ( list != null ) {
            person.setBootcampId( new ArrayList<Long>( list ) );
        }

        return person;
    }
}
