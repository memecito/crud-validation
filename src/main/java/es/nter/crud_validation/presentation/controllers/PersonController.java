package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.services.PersonService;
import es.nter.crud_validation.presentation.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Target;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<Iterable<PersonDto>> getAllPerson(
            @RequestParam (defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize){
        return ResponseEntity.ok(personService.getAllPerson(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long id){
        PersonDto personDto= personService.getPersonById(id);
        if (personDto==null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(personDto);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<PersonDto> getPersonByName(@RequestParam("name") String name){
        PersonDto personDto= personService.getPersonByName(name);
        if (personDto==null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(personDto);
        }
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(personDto));
    }

    @PutMapping
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto personDto){
        return ResponseEntity.ok(personService.updatePerson(personDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updateField(
            @PathVariable Long id,
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "city", defaultValue = "", required = false) String city){
        return ResponseEntity.ok(personService.updateParam(id, name, city));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDto> deletePersonById(@PathVariable Long id){
        if (personService.getPersonById(id) != null) {
            return ResponseEntity.ok(personService.deletePersonById(id));
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
