package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.services.PersonService;
import es.nter.crud_validation.presentation.dto.PersonDto;
import es.nter.crud_validation.presentation.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPerson(
            @RequestParam (defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize){
        return ResponseEntity.ok(personService.getAllPerson(pageNumber, pageSize).stream().map(personMapper::toDtoStandard).collect(Collectors.toList()));
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
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personMapper.toDtoStandard(
                        personService.addPerson(
                                personMapper.toModelStandard(
                                        personDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(
            @PathVariable long id,
            @RequestBody PersonDto personDto){
        return ResponseEntity.ok(
                personMapper.toDtoStandard(
                        personService.updatePerson(id,
                                personMapper.toModelStandard(
                                        personDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonById(@PathVariable Long id){
        personService.deletePersonById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();


    }


}
