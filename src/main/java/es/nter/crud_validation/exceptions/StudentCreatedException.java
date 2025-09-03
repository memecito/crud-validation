package es.nter.crud_validation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class StudentCreatedException extends RuntimeException {
    public StudentCreatedException(String message) {
      super(message);
    }
}
