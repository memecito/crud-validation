package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.services.PersonService;
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

    @GetMapping
    public ResponseEntity<List<PersonOutDtoMini>> getAllPersonActive(
            @RequestParam (defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize){
        return ResponseEntity.ok(
                personService.getAllPersonActive(pageNumber,pageSize)
                        .stream().map(personMapper::toDtoMini)
                        .collect(Collectors.toList())) ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonOutDtoMini>> getAllPerson(
            @RequestParam (defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize){
        //List<Person> list = personService.getAllPerson(pageNumber, pageSize);
        return ResponseEntity.ok(
                personService.getAllPerson(pageNumber, pageSize)
                        .stream().map(personMapper::toDtoMini)
                            .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long id){
        PersonDto personDto= personMapper.toDtoStandard(personService.getPersonById(id));
        if (personDto==null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(personDto);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<PersonDto> getPersonByName(@RequestParam("name") String name){
        PersonDto personDto= personMapper.toDtoStandard(personService.getPersonByName(name));
        if (personDto==null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(personDto);
        }
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonInputDto personInputDto){
        return
                ResponseEntity.status(HttpStatus.CREATED).body(
                        personMapper.toDtoStandard(
                            personService.addPerson(
                                personMapper.toModelStandard(personInputDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(
            @PathVariable long id,
            @RequestBody PersonInputDto personInputDto){
        return ResponseEntity.ok(
                personMapper.toDtoStandard(
                    personService.updatePerson(id,
                        personMapper.toModelStandard(personInputDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonById(@PathVariable Long id){
       personService.deletePersonById(id);
       return ResponseEntity.ok().build();
    }
}
