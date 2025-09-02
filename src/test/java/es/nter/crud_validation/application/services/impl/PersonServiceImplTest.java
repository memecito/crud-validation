package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.DataProviders;
import es.nter.crud_validation.application.mappers.PersonMapper;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;


    @InjectMocks
    private PersonServiceImpl personService;


    @Test
    void getAllPerson() {
        //Given
        List<Person> personList = DataProviders.personListMock();
        Pageable pageable = PageRequest.of(0, 5);
        Page<Person> personPage = new PageImpl<>(personList, pageable, personList.size());
        //When
        when(personRepository.findAll(pageable))
                .thenReturn(personPage);
        List<Person> personResoult = personService.getAllPerson(0, 5);
        //Then
        assertNotNull(personResoult);
        assertFalse(personResoult.isEmpty());
    }

    @Test
    void getAllPersonActive() {
        //Given

        //When
        when(personRepository.findByActiveTrue(PageRequest.of(0, 5)))
                .thenReturn(DataProviders.personListMock().stream().filter(p -> p.getActive() == true).toList());
        List<Person> personResoult = personService.getAllPersonActive(0, 5);
        //Then
        assertNotNull(personResoult);
        assertFalse(personResoult.isEmpty());
    }

    @Test
    void getPersonNobody() {
        //Given
        PageRequest pageRequest = PageRequest.of(0, 5);
        //When
        when(personRepository.findByRolIs(Rol.NOBODY, pageRequest)).thenReturn(
                DataProviders.personListMock().stream()
                        .filter(p -> p.getRol() == Rol.NOBODY).toList());

        List<Person> personResoult = personService.getPersonNobody(0, 5);
        //Then
        assertNotNull(personResoult);
        assertFalse(personResoult.isEmpty());
        verify(personRepository).findByRolIs(Rol.NOBODY, pageRequest);
    }

    @Test
    void getPersonById() {
        //Given
        Long id = 1L;
        //When
        when(personRepository.findById(anyLong())).thenReturn(DataProviders.personMock());
        Person personResult = personService.getPersonById(id);
        //Then
        assertNotNull(personResult);
        verify(personRepository).findById(anyLong());
    }

    @Test
    void getPersonByName() {
        //Given
        String name = "nombre";
        //When
        when(personRepository.findByName(any(String.class))).thenReturn(DataProviders.personMock());
        Person personResult = personService.getPersonByName(name);

        //Then
        assertNotNull(personResult);
        verify(personRepository).findByName(any(String.class));
    }

    @Test
    void getPersonByUserName() {
        //Given
        String userName = "nombre";
        //When
        when(personRepository.findByUsername(any(String.class))).thenReturn(DataProviders.personMock());
        Person personResult = personService.getPersonByUserName(userName);

        //Then
        assertNotNull(personResult);
        verify(personRepository).findByUsername(any(String.class));
    }


    @Test
    void addPerson() {

        Person p = buildPerson2();
        when(personRepository.save(p)).thenReturn(buildPerson());

        Person resultado = personService.addPerson(p);
        Assertions.assertEquals(buildPerson().getUsername(), resultado.getUsername());
    }

    @Test
    void updatePerson() {
        //Given
        Long id = 1L;
        Person personMock = DataProviders.personMock().get();
        //When
        when(personRepository.findById(anyLong())).thenReturn(DataProviders.personMock());
        when(personMapper.update(any(Person.class), any(Person.class))).thenReturn(personMock);
        Person personResult = personService.updatePerson(id, personMock);
        //Then
        assertNotNull(personResult);
        verify(personMapper).update(any(Person.class), any(Person.class));

    }

    @Test
    void deletePersonById() {
        //Given
        Long id = 1L;
        //When
        when(personRepository.findById(anyLong())).thenReturn(DataProviders.personMock());
        personService.deletePersonById(id);
        //Then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(personRepository).delete(any(Person.class));

    }

    private Person buildPerson() {
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

    private Person buildPerson2() {
        return new Person(
                (long) 1,
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