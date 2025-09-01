package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.mappers.PersonMapper;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.domain.models.Teacher;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private Student student;
    @Mock Teacher teacher;

    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonMapper personMapper;



    private Person person2 = new Person(
            "username2",
            "password2",
            "name",
            "surname",
            "personalEmail",
            "companyEmail",
            "city",
            true,
            new Timestamp(2025)
    );


    @Test
    void getAllPerson() {


       //given(personRepository.findAll()).willReturn(List.of(person1,person2,person1,person2,person1));



        List<Person> personList= personService.getAllPerson(1,1);

        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(5);
        verify(personRepository).findAll();


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