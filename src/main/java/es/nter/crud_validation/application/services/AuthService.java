package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.presentation.dto.auth.AuthTokens;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;

public interface AuthService {
    AuthTokens register(Person person) throws BadRequestException;

    AuthTokens authenticate(Person peron);

    AuthTokens refresh(HttpServletRequest request);
}
