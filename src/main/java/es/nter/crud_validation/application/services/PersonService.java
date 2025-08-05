package es.nter.crud_validation.application.services;

import es.nter.crud_validation.presentation.dto.PersonDto;


public interface PersonService {

    PersonDto getPersonById(Long id);
    PersonDto getPersonByName(String name);
    PersonDto addPerson(PersonDto person);
    PersonDto deletePersonById(Long id);
    Iterable<PersonDto> getAllPerson(int pageNumber, int pageSize);
    PersonDto updatePerson(PersonDto person);

    PersonDto updateParam(Long id, String name, String city);
}
