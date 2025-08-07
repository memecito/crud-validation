package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Person;

import java.util.List;


public interface PersonService {

    Person getPersonById(Long id);
    Person getPersonByName(String name);
    Person addPerson(Person person);
    Person deletePersonById(Long id);
    List<Person> getAllPerson(int pageNumber, int pageSize);
    Person updatePerson(Person person);

    Person updateParam(Long id, String name, String city);
}
