package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.infraestructure.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                //.roles(getRoles(user.getRol()))
                .build();
    }

    private String getRoles(String rol) {
        if (rol.isEmpty())
            return "ADMIN";

        return "USER";
    }
}
