package marlon.leoner.technical.assessment.config;

import marlon.leoner.technical.assessment.model.Error;
import marlon.leoner.technical.assessment.model.exception.ObjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Error handleGenericException(Exception ex) {
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFound.class)
    public Error handleObjectNotFoundException(Exception ex) {
        return new Error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
