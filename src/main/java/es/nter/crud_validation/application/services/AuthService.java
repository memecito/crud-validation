package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.presentation.dto.auth.AuthTokens;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    AuthTokens register(Person person);

    AuthTokens authenticate(Person peron);

    AuthTokens refresh(HttpServletRequest request);
}
