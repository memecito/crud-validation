package es.nter.crud_validation.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RestControllerAdvice
public class UnprocessableEntityException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleValidationException(MethodArgumentNotValidException exception){
        Map<String, String> errors= new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(
                (error)->{
                    String key= ((FieldError)error).getField();
                    String message= error.getDefaultMessage();
                    errors.put(key,message);
                }
        );
        CustomError customError= new CustomError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Faltan campos",
                errors.toString()
        );
        return new ResponseEntity<>(customError,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFound(
            EntityNotFoundException ex){
        CustomError customError= new CustomError(
                HttpStatus.NOT_FOUND.value(),
                "Entidad no encontrada",
                ex.getMessage()
        );
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }
}
