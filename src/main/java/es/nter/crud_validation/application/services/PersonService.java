package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Person;


import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface PersonService {

    List<Person> getAllPerson(int pageNumber, int pageSize);

    List<Person> getAllPersonActive(int pageNumber, int pageSize);

    List<Person> getPersonNobody(int pageNumber, int pageSize);

    List<Person> getAdvandedPerson(Map<String, String> params)

    Person getPersonById(Long id);

    Person getPersonByName(String name);

    Person getPersonByUserName(String userName);

    Person addPerson(Person person);

    Person updatePerson(long id, Person person);

    void deletePersonById(Long id);


}
