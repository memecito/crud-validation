package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.services.PersonService;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;
import es.nter.crud_validation.presentation.dto.PersonDto;
import es.nter.crud_validation.presentation.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Override
    public List<Person> getAllPerson(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).getContent().stream()
                .toList();
    }

    @Override
    public Person getPersonById(Long id) {
            return personRepository.findById(id).orElseThrow(()-> new RuntimeException("Persona no encontrada"));
    }

    @Override
    public Person getPersonByName(String name) {
           return personRepository.findByName(name);
    }

    @Override
    @Transactional
    public Person addPerson(Person person) {
           return  personRepository.save(person);
    }

    @Override
    @Transactional
    public void deletePersonById(Long id) {
        personRepository.delete(getPersonById(id));
    }

    @Override
    @Transactional
    public Person updatePerson(Long id, Person person) {
        return personRepository.save(personMapper.update(this.getPersonById(id), person));
    }
}
