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

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;

    @Autowired
    PersonRepository personRepository;

    @Override
    public Iterable<PersonDto> getAllPerson(int pageNumber, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).getContent().stream()
                .map((personMapper::toDtoStandard)).toList();
    }

    @Override
    public PersonDto getPersonById(Long id) {
        try{
            return personRepository.findById(id).map(personMapper::toDtoStandard).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PersonDto getPersonByName(String name){
        try{
            return personMapper.toDtoStandard(personRepository.findByName(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PersonDto addPerson(PersonDto person) {
        try{
            personRepository.save(personMapper.toModelStandard(person));
            return personMapper.toDtoStandard(
                    personRepository.findByName(person.getName()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PersonDto deletePersonById(Long id) {
        try{
           Optional<Person> person= personRepository.findById(id);
           if(person.isPresent()){
               personRepository.findById(id).ifPresent(personRepository::delete);
                return personMapper.toDtoStandard(person.get());
           }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public PersonDto updatePerson(PersonDto person) {
        Person person1= personRepository.findById(person.getId()).orElse(null);
        assert person1 != null;
        if (!Objects.equals(person1.getName(), person.getName())) {
            person1.setName(person.getName());
        }
        if(!Objects.equals(person1.getCity(), person.getCity()))
        {
            person1.setCity(person.getCity());
        }
        return personMapper.toDtoStandard(person1);
    }

    @Override
    public PersonDto updateParam(Long id, String name, String city) {
        Person person = personMapper.toModelStandard(getPersonById(id));
        if(!Objects.equals(name,"")){
            person.setName(name);
        }
        if(!Objects.equals(city, "")){
            person.setCity(city);
        }
        return personMapper.toDtoStandard(personRepository.update(person));
    }
}
