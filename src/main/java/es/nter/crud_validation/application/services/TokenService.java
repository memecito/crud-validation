package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Tokens;
import org.apache.coyote.BadRequestException;

public interface TokenService {
    Tokens createToken(Person person);
    Person verifyToken(String verificationToken) throws BadRequestException;
}
