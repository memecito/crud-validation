package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.mappers.PersonMapper;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.cglib.core.Local;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;
    private PersonMapper personMapper;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void getAllPerson() {
        List<Person> personList= new ArrayList<>();
        Mockito.when(personService.getAllPerson(1,1)).thenReturn(personList);
        final List<Person> resultado=
                personService.getAllPerson(1,1);
    }

    @Test
    void getAllPersonActive() {
    }

    @Test
    void getPersonNobody() {
    }

    @Test
    void getPersonById() {
    }

    @Test
    void getPersonByName() {
    }

    @Test
    void getPersonByUserName() {
    }

    @Test
    void addPerson() {
        Person personEsperada= new Person(
        );
                0,
                "user_ana3",
                "12341234",
                "ana",
                "gomez",
                "anagomez@mail.com",
                "anagomez@trabajo.com",
                "Jaen",
                true,
                LocalDate.now(),
                "url",
                LocalDate.now(),
                Rol.NOBODY);
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePersonById() {
    }
}