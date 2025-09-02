package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.mappers.TeacherMapper;
import es.nter.crud_validation.application.services.PersonService;
import es.nter.crud_validation.application.services.TeacherService;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.presentation.dto.person.PersonDto;
import es.nter.crud_validation.presentation.dto.person.PersonInputDto;
import es.nter.crud_validation.presentation.dto.person.PersonOutDtoMini;
import es.nter.crud_validation.application.mappers.PersonMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @GetMapping
    public ResponseEntity<List<PersonOutDtoMini>> getAllPersonActive(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize) {
        return ResponseEntity.ok(
                personService.getAllPersonActive(pageNumber, pageSize)
                        .stream().map(personMapper::toDtoMini)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/sindto")
    public ResponseEntity<List<Person>> getAllPersonSinDto(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize) {

        return ResponseEntity.ok(
                personService.getAllPersonActive(pageNumber, pageSize)
        );
    }

    @GetMapping("/nobody")
    public ResponseEntity<List<PersonOutDtoMini>> getPersonNobody(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize) {

        return ResponseEntity.ok(
                personService.getPersonNobody(pageNumber, pageSize)
                        .stream().map(personMapper::toDtoMini)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonOutDtoMini>> getAllPerson(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "10", required = false) int pageSize) {

        return ResponseEntity.ok(
                personService.getAllPerson(pageNumber, pageSize)
                        .stream().map(personMapper::toDtoMini)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {

        Person person = personService.getPersonById(id);

        return switch (person.getRol()) {
            case STUDENT -> ResponseEntity.ok(personMapper.toDtoStudent(person));
            case TEACHER -> ResponseEntity.ok(
                    personMapper.toDtoTeacher(person));
            default -> ResponseEntity.ok(personMapper.toDtoStandard(person));
        };
    }

    @GetMapping("/name")
    public void getPersonByName(@RequestParam("name") String name) {
        getPersonById(personService.getPersonByName(name).getId());
        ;
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonInputDto personInputDto) {
        return
                ResponseEntity.status(HttpStatus.CREATED).body(
                        personMapper.toDtoStandard(
                                personService.addPerson(
                                        personMapper.toModelStandard(personInputDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(
            @PathVariable long id,
            @RequestBody PersonInputDto personInputDto) {
        return ResponseEntity.ok(
                personMapper.toDtoStandard(
                        personService.updatePerson(id,
                                personMapper.toModelStandard(personInputDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonById(@PathVariable Long id) {
        personService.deletePersonById(id);
        return ResponseEntity.ok().body("persona eliminada");
    }
}
