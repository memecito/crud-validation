package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.mappers.PersonMapper;
import es.nter.crud_validation.application.services.PersonService;
import es.nter.crud_validation.application.services.TokenService;
import es.nter.crud_validation.domain.models.AuthTokens;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.domain.models.Tokens;
import es.nter.crud_validation.exceptions.BadUserException;
import es.nter.crud_validation.exceptions.DeletePersonException;
import es.nter.crud_validation.exceptions.EntityDuplicateException;
import es.nter.crud_validation.exceptions.EntityNotFoundException;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;
import es.nter.crud_validation.infraestructure.repositories.TokensRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {


    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    @Override
    public List<Person> getAllPerson(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).getContent().stream().toList();
    }

    @Override
    public List<Person> getAllPersonActive(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findByActiveTrue(pageRequest).stream().toList();
    }

    @Override
    public List<Person> getPersonNobody(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findByRolIs(Rol.NOBODY, pageRequest).stream().toList();
    }

    @Override
    public Person getPersonById(Long id) {

        return personRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("persona", id));
    }

    @Override
    public Person getPersonByName(String name) {

        return personRepository.findByName(name)
                .orElseThrow(
                        () -> new EntityNotFoundException(name)
                );
    }

    @Override
    public Person getPersonByUserName(String userName) {

        return personRepository.findByUsername(userName)
                .orElseThrow(
                        () -> new EntityNotFoundException(userName)
                );
    }

    @Override
    @Transactional
    public Tokens addPerson(Person person) {

        personRepository.findByUsername(person.getUsername())
                .ifPresent(p -> {
                    throw new EntityDuplicateException("El usuario: " + p.getUsername() + " ya existe");
                });
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Person personSaved = personRepository.save(person);

        return tokenService.createToken(personSaved);
    }

    @Override
    public AuthTokens authenticate(Person person) {
        Person personFound = personRepository.findByUsername(person.getUsername())
                .orElseThrow(() -> new BadUserException("Usuario novalido"));
        UserDetails userDetails = userDetailsService.loadUserByUsername(personFound.getUsername());

        return new AuthTokens(
                jwtService.generateAccessToken(userDetails),
                jwtService.generateRefreshToken(userDetails)
        );
    }

    @Override
    @Transactional
    public Person updatePerson(long id, Person person) {
        return personMapper.update(getPersonById(id), person);
    }

    @Override
    @Transactional
    public void deletePersonById(Long id) {
        Person p = getPersonById(id);
        if (p.getRol() == Rol.NOBODY) {
            personRepository.delete(getPersonById(id));
        } else {
            throw new DeletePersonException(p.getRol().toString());
        }

    }


}
