package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Person;


import java.util.List;


public interface PersonService {

    List<Person> getAllPerson(int pageNumber, int pageSize);
    List<Person> getAllPersonActive(int pageNumber, int pageSize);

    Person getPersonById(Long id);
    Person getPersonByName(String name);

    Person addPerson(Person person);

    Person deletePersonById(Long id);

    Person updatePerson(Person person);
    Person updateParam(Long id, String name, String city);

}
