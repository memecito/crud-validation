package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.DataProviders;
import es.nter.crud_validation.application.mappers.PersonMapper;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.domain.models.Teacher;
import es.nter.crud_validation.error.EntityNotFoundException;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;
    @Mock
    private Student student;
    @Mock Teacher teacher;

    @InjectMocks
    private PersonServiceImpl personService;





    @Test
    void getAllPerson() {
        //Given

        //When
        when(personRepository.findAll(PageRequest.of(1,5))).thenReturn((Page<Person>) DataProviders.personListMock());
        List<Person> personResoult= personService.getAllPerson(1,1);
        //Then
        assertNotNull(personResoult);
        assertFalse(personResoult.isEmpty());
        //verify(personRepository).findAll();
    }

    @Test
    void getAllPersonActive() {
    }

    @Test
    void getPersonNobody() {
    }

    @Test
    void getPersonById() {
        //Given
        Long id=1L;
        //When
        when(personRepository.findById(anyLong())).thenReturn(DataProviders.personMock());
        Person personResult= personService.getPersonById(id);
        //Then
        assertNotNull(personResult);
    }

    @Test
    void getPersonByName() {
    }

    @Test
    void getPersonByUserName() {
        Person p= buildPerson2();
        when(personRepository.findByUsername("nombre")).thenReturn(Optional.of(p));
        Person resultado= personService.getPersonByUserName("nombre");
        Assertions.assertEquals(resultado, buildPerson2());
    }

    @Test
    void addPerson() {

        Person p= buildPerson2();
        when(personRepository.save(p)).thenReturn(buildPerson());

        Person resultado=  personService.addPerson(p);
        Assertions.assertEquals(buildPerson().getUsername(), resultado.getUsername());
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePersonById() {
    }

    private Person buildPerson(){
        return new Person(
                "username",
                "password",
                "name",
                "surname",
                "personalEmail",
                "companyEmail",
                "city",
                true,
                new Timestamp(2025)
        );
    }
    private Person buildPerson2(){
        return new Person(
                (long)1,
                "username",
                "password",
                "name",
                "surname",
                "personalEmail",
                "companyEmail",
                "city",
                true,
                new Timestamp(2025)
        );
    }
}