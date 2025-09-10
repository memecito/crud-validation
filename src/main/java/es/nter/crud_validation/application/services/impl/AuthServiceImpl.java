package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.services.AuthService;
import es.nter.crud_validation.configuration.security.JwtService;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.exceptions.PersonNameException;
import es.nter.crud_validation.exceptions.UnauthenticatedException;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;
import es.nter.crud_validation.presentation.dto.auth.AuthTokens;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    public AuthTokens register(Person person) {
        if (personRepository.existsByUsername(person.getUsername()))
            throw new BadRequestException("User name exists");

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRol(Rol.ADMIN);

        Person p = personRepository.save(person);
        UserDetails personDetails = userDetailsService.loadUserByUsername(p.getUsername());

        return new AuthTokens(
                jwtService.generateAccessToken(personDetails),
                jwtService.generateRefreshToken(personDetails)
        );
    }

    @Override
    public AuthTokens authenticate(Person person) throws BadRequestException {
        Person foundPerson = personRepository.findByUsername(person.getUsername()).orElseThrow(
                () -> new BadRequestException("Invalid credential AS-1")
        );
        if (!passwordEncoder.matches(person.getPassword(), foundPerson.getPassword()))
            throw new BadRequestException("Invalid credential AS-2");
        UserDetails userDetails = userDetailsService.loadUserByUsername(foundPerson.getUsername());

        return new AuthTokens(
                jwtService.generateAccessToken(userDetails),
                jwtService.generateRefreshToken(userDetails)
        );
    }

    @Override
    public AuthTokens refresh(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null)
            throw new UnauthenticatedException("No refresh Token present");

        String refreshToken = Arrays.stream(cookies)
                .filter(c -> REFRESH_TOKEN_COOKIE.equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new UnauthenticatedException("Refresh token missing"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(refreshToken));


        return null;
    }
}
