package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.mappers.TeacherMapper;
import es.nter.crud_validation.application.services.PersonService;
import es.nter.crud_validation.application.services.TeacherService;
import es.nter.crud_validation.application.services.impl.JwtService;
import es.nter.crud_validation.domain.models.AuthTokens;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Tokens;
import es.nter.crud_validation.presentation.dto.auth.AuthInDto;
import es.nter.crud_validation.presentation.dto.auth.AuthOutDto;
import es.nter.crud_validation.presentation.dto.person.PersonDto;
import es.nter.crud_validation.presentation.dto.person.PersonInputDto;
import es.nter.crud_validation.presentation.dto.person.PersonOutDtoMini;
import es.nter.crud_validation.application.mappers.PersonMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    private final JwtService jwtService;

    public final static String REFRESH_TOKEN_COOKIE = "refreshToken";


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

    @PostMapping("/register")
    public ResponseEntity<AuthOutDto> createPerson(@Valid @RequestBody PersonInputDto personInputDto, HttpServletResponse response) {
        Tokens tokens = personService.addPerson(
                personMapper.toModelStandard(personInputDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new AuthOutDto(
                        HttpStatus.CREATED.value(),
                        tokens.getToken(),
                        jwtService.getAccessTokenExpiration()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthOutDto> login(@Valid @RequestBody AuthInDto authInDto, HttpServletResponse response) {
        AuthTokens authTokens = personService.authenticate(personMapper.toModelAuth(authInDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new AuthOutDto(
                        HttpStatus.CREATED.value(),
                        authTokens.accesToken(),
                        jwtService.getAccessTokenExpiration()
                )
        );
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

    private void setRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie refreshCookie = ResponseCookie.from(REFRESH_TOKEN_COOKIE, refreshToken)
                .httpOnly(true)
                .secure(jwtService.isSecureAccess()) // En entornos productivos debería ir en true
                .path("/auth/refresh")
                .maxAge(Duration.ofDays(7))
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "DENY");

    }
}
