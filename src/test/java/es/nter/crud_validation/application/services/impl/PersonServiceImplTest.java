package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.domain.models.Teacher;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private Student student;
    @Mock Teacher teacher;

    @InjectMocks
    private PersonServiceImpl personService;


    @Test
    void getAllPerson() {
        /*
        Person person1 = new Person(

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
        Person person2 = new Person(
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
        given(personRepository.findAll()).willReturn(List.of(person1,person2,person1,person2,person1));



        List<Person> personList= personService.getAllPerson(1,1);

        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(5);
        verify(personRepository).findAll();

         */
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
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePersonById() {
    }
}