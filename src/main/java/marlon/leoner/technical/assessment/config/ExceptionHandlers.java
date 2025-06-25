package marlon.leoner.technical.assessment.config;

import marlon.leoner.technical.assessment.domain.exception.BaseException;
import marlon.leoner.technical.assessment.domain.model.Error;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlers {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Error handleGenericException(Exception ex) {
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<Error> handleException(BaseException ex) {
        Error error = new Error(ex.getCode(), ex.getMessage());
        HttpStatusCode statusCode = HttpStatusCode.valueOf(ex.getCode());

        return new ResponseEntity<>(error, statusCode);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return new Error(HttpStatus.BAD_REQUEST.value(), messages.toString());
    }
}
