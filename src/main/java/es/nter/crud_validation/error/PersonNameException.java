package es.nter.crud_validation.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PersonNameException extends RuntimeException {
    public PersonNameException(String message) {
        super("el campo nombre no cumple con los requisitos");
    }
}
