package com.reactive.programming.challenge.domain.util;

import com.reactive.programming.challenge.domain.enums.TechnicalMessage;
import com.reactive.programming.challenge.domain.exceptions.BusinessException;
import com.reactive.programming.challenge.domain.model.Bootcamp;
import com.reactive.programming.challenge.domain.model.Person;
import com.reactive.programming.challenge.domain.spi.IWebClientPersistencePort;
import com.reactive.programming.challenge.domain.usecase.PersonUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public class Validator {
    private static final Logger log = LoggerFactory.getLogger(PersonUseCase.class);
    private final IWebClientPersistencePort webClientPersistencePort;

    public Validator(IWebClientPersistencePort webClientPersistencePort) {
        this.webClientPersistencePort = webClientPersistencePort;
    }

    public Mono<Person> validateUser(Person person) {
        log.info("validateUser, person: {}", person);
        if (person.getName() == null || person.getName().length() > Constants.NAME_CHARACTER_LIMIT)
            Mono.error(new BusinessException(TechnicalMessage.NAME_CHARACTER_LIMIT));

        if (person.getEmail() == null || person.getEmail().length() > Constants.EMAIL_CHARACTER_LIMIT)
            Mono.error(new BusinessException(TechnicalMessage.DESCRIPTION_CHARACTER_LIMIT));

        if (person.getName().matches("^\\d+$"))
            Mono.error(new BusinessException(TechnicalMessage.NOT_ONLY_NUMBERS));

        return Mono.just(person);
    }

    public Mono<Void> validateBootcamps(List<Long> bootcampIds) {
        if (bootcampIds.size() > Constants.BOOTCAMPS_LIMIT)
            Mono.error(new BusinessException(TechnicalMessage.BOOTCAMPS_LIMIT));

        return Flux.fromIterable(bootcampIds)
                .flatMap(webClientPersistencePort::getBootcamp)
                .collectList()
                .flatMap(this::checkOverlappingBootcamps);
    }

    public Mono<Void> validateBootcam(List<Long> bootcampId) {
        if (bootcampId.isEmpty()){
            Mono.error(new BusinessException(TechnicalMessage.MIN_NUMBER_CAPACITIES));
        }
        if (bootcampId.size() > 4){
            Mono.error(new BusinessException(TechnicalMessage.MAX_NUMBER_CAPACITIES));
        }
        if (hasDuplicates(bootcampId)) {
            Mono.error(new BusinessException(TechnicalMessage.DUPLICATED_CAPACITIES));
        }
        return Flux.fromIterable(bootcampId)
                .flatMap(id -> webClientPersistencePort.getBootcamp(id)
                        .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.CAPACITY_NOT_FOUND)))
                        .doOnNext(tech -> log.info("Bootcamp encontrada {}", tech.getName()))
                        .onErrorMap(ex -> {
                            log.error("Error al buscar Bootcamp con ID {}: {}", id, ex.getMessage());
                            return new BusinessException(TechnicalMessage.CAPACITY_NOT_FOUND);
                        })
                )
                .then();
    }

    private boolean hasDuplicates(List<Long> list) {
        return list.size() != new HashSet<>(list).size();
    }

    private Mono<Void> checkOverlappingBootcamps(List<Bootcamp> bootcamps) {
        for (int i = 0; i < bootcamps.size(); i++) {
            Bootcamp b1 = bootcamps.get(i);
            LocalDate start1 = b1.getLaunchDate();
            LocalDate end1 = start1.plusDays(b1.getDurationInWeeks()* 7L);

            for (int j = i + 1; j < bootcamps.size(); j++) {
                Bootcamp b2 = bootcamps.get(j);
                LocalDate start2 = b2.getLaunchDate();
                LocalDate end2 = start2.plusDays(b2.getDurationInWeeks()* 7L);

                boolean overlaps = !(end1.isBefore(start2) || start1.isAfter(end2));
                if (overlaps) {
                    Mono.error(new BusinessException(TechnicalMessage.BOOTCAMPS_DATE_CONFLICT));
                }
            }
        }
        return Mono.empty();
    }
}
