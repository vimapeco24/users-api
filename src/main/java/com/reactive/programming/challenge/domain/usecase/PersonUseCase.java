package com.reactive.programming.challenge.domain.usecase;

import com.reactive.programming.challenge.domain.api.IPersonServicePort;
import com.reactive.programming.challenge.domain.enums.TechnicalMessage;
import com.reactive.programming.challenge.domain.exceptions.BusinessException;
import com.reactive.programming.challenge.domain.model.Bootcamp;
import com.reactive.programming.challenge.domain.model.Person;
import com.reactive.programming.challenge.domain.model.PersonBootcamp;
import com.reactive.programming.challenge.domain.spi.IPersonBootcampPersistencePort;
import com.reactive.programming.challenge.domain.spi.IPersonPersistencePort;
import com.reactive.programming.challenge.domain.spi.IWebClientPersistencePort;
import com.reactive.programming.challenge.domain.util.Constants;
import com.reactive.programming.challenge.domain.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public class PersonUseCase implements IPersonServicePort {
    private static final Logger log = LoggerFactory.getLogger(PersonUseCase.class);
    private final IPersonPersistencePort personPersistencePort;
    private final IPersonBootcampPersistencePort personBootcampPersistencePort;
    private final IWebClientPersistencePort webClientPersistencePort;
    private final Validator validator;

    public PersonUseCase(IPersonPersistencePort personPersistencePort, IPersonBootcampPersistencePort personBootcampPersistencePort, IWebClientPersistencePort webClientPersistencePort) {
        this.personPersistencePort = personPersistencePort;
        this.personBootcampPersistencePort = personBootcampPersistencePort;
        this.webClientPersistencePort = webClientPersistencePort;
        this.validator = new Validator(webClientPersistencePort);
    }

    @Override
    public Mono<Person> registerPerson(Person person) {
        log.info("Registering person: {}", person.getName());
        validator.validateUser(person);
        return validator.validateBootcam(person.getBootcampId())
                .then(personPersistencePort.createPerson(person))
                .flatMap(savedBootcamp -> {
                    List<PersonBootcamp> relations = person.getBootcampId().stream()
                            .map(personId -> new PersonBootcamp(null, savedBootcamp.getId(), personId))
                            .toList();

                    return personBootcampPersistencePort.createAll(relations)
                            .then(Mono.just(savedBootcamp));
                });
    }

    @Override
    public Mono<Person> getPerson(Long id) {
        log.info("Searching technology with id: {}", id);
        return personPersistencePort.getPerson(id);
    }

    @Override
    public Mono<Person> enrollInABootcamp(Long id, List<Long> bootcampIds) {
        return personPersistencePort.getPerson(id)
                .flatMap(person -> {
                    validator.validateUser(person);
                    validator.validateBootcamps(bootcampIds);
                    List<PersonBootcamp> relations = bootcampIds.stream()
                            .map(bootcampId -> new PersonBootcamp(null, id, bootcampId))
                            .toList();
                    return personBootcampPersistencePort.createAll(relations).then(Mono.just(person));
                });

    }

    @Override
    public Mono<Void> deleteCapacity(Long id) {
        return personPersistencePort.deleteCapacity(id);
    }

    @Override
    public Flux<Person> getTechnologiesByIdIn(List<Long> ids) {
        return personPersistencePort.findByIdIn(ids);
    }
}
