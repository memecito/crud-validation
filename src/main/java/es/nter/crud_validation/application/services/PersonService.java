package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.presentation.dto.PersonDto;

import java.util.List;


public interface PersonService {

    List<Person> getAllPerson(int pageNumber, int pageSize);
    Person getPersonById(Long id);
    Person getPersonByName(String name);

    Person addPerson(Person person);

    Person updatePerson(Long id,Person person);

    void deletePersonById(Long id);

}
