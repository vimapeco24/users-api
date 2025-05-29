package com.reactive.programming.challenge.domain.usecase;

import com.reactive.programming.challenge.domain.model.Person;
import com.reactive.programming.challenge.domain.spi.IPersonPersistencePort;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TechnologyUseCaseTest {
    @Mock
    private IPersonPersistencePort technologyPersistencePort;

    @InjectMocks
    private PersonUseCase technologyUseCase;
    private Person person1;
    private Person person2;
    private Person person3;
    private Person person4;

    @BeforeAll
    public void setup() {

        person1 = new Person("Spring Boot", "Framework de Java que simplifica la creación de aplicaciones basadas en Spring, permitiendo una configuración mínima y una rápida puesta en marcha.",List.of(1L));
        person2 = new Person("React.......................................................................", "Biblioteca de JavaScript para construir interfaces de usuario interactivas.", List.of(1L));
        person3 = new Person("624231123", "Servicios en la nube de Amazon de computación, almacenamiento, bases de datos, etc.",List.of(1L));
        person4 = new Person("HTML", "Lenguaje de marcado estándar para crear y estructurar páginas web y aplicaciones web.",List.of(1L));
    }

//    @Test
//    @Order(1)
//    public void createTechnologyWithInvalidDescriptionShouldThrowBadRequest() {
//        BusinessException exception = assertThrows(BusinessException.class,
//                () -> technologyUseCase.registerTechnology(technology1));
//        assertEquals("Technology description must be less than 90 characters", exception.getTechnicalMessage().getMessage());
//    }
//
//    @Test
//    @Order(2)
//    public void createTechnologyWithInvalidNameShouldThrowBadRequest() {
//        BusinessException exception = assertThrows(BusinessException.class,
//                () -> technologyUseCase.registerTechnology(technology2));
//        assertEquals("Technology name must be less than 50 characters", exception.getTechnicalMessage().getMessage());
//    }
//
//    @Test
//    @Order(3)
//    public void createTechnologyWithOnlyNumbersInNameShouldThrowBadRequest() {
//        BusinessException exception = assertThrows(BusinessException.class,
//                () -> technologyUseCase.registerTechnology(technology3));
//        assertEquals("Technology name cannot contain only numbers", exception.getTechnicalMessage().getMessage());
//    }
//
//    @Test
//    @Order(4)
//    public void createTechnologyWithValidDataShouldReturnTechnology() {
//
//        // Simular que no existe previamente la tecnología
//        when(technologyPersistencePort.existsByName(technology4.getName()))
//                .thenReturn(Mono.just(false));
//
//        // Simular la creación exitosa
//        when(technologyPersistencePort.createPerson(technology4))
//                .thenReturn(Mono.just(technology4));
//
//        // Act
//        Mono<Technology> result = technologyUseCase.registerTechnology(technology4);
//
//        // Assert
//        StepVerifier.create(result)
//                .expectNext(technology4)
//                .verifyComplete();
//    }
}
