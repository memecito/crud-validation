package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.services.PersonService;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.error.EntityNotFoundException;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {



    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> getAllPerson(int pageNumber, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).getContent().stream().toList();
    }

    @Override
    public List<Person> getAllPersonActive(int pageNumber, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);
        return personRepository.findByActiveTrue(pageRequest).stream().toList();

    }

    @Override
    public Person getPersonById(Long id) {
        try{
            return personRepository.findById(id)
                    .orElseThrow(
                            ()-> new EntityNotFoundException(id)
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Person getPersonByName(String name){
        try{
            return personRepository.findByName(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Person addPerson(Person person) {
        try{
            personRepository.save(person);
                    return  personRepository.findByName(person.getName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Person deletePersonById(Long id) {
        try{
           Optional<Person> person= personRepository.findById(id);
           if(person.isPresent()){
               personRepository.findById(id).ifPresent(personRepository::delete);
               return person.get();
           }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Transactional
    @Override
    public Person updatePerson(Person person) {
        Person person1= personRepository.findById(person.getId()).orElse(null);
        assert person1 != null;
        if (!Objects.equals(person1.getName(), person.getName())) {
            person1.setName(person.getName());
        }
        if(!Objects.equals(person1.getCity(), person.getCity()))
        {
            person1.setCity(person.getCity());
        }
        return person1;
    }

    @Override
    public Person updateParam(Long id, String name, String city) {
        Person person = getPersonById(id);

        if(!Objects.equals(name,"")){
            person.setName(name);
        }
        if(!Objects.equals(city, "")){
            person.setCity(city);
        }
        return personRepository.save(person);
    }


}
