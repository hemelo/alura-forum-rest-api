package br.com.alura.forum.infra.interceptor;

import br.com.alura.forum.persistence.projections.ValidationErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;
import javax.validation.UnexpectedTypeException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class StatusHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationErrorDto> argumentInvalid(MethodArgumentNotValidException e){
       return e.getBindingResult()
               .getFieldErrors()
               .stream()
               .map(field -> new ValidationErrorDto(field, messageSource))
               .collect(Collectors.toList());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(){
        return  ResponseEntity.notFound().build();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity badAuth() {
        return ResponseEntity.badRequest().build();
    }

    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity wrongAction() {
        return ResponseEntity.status(405).build();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity internalValidationError() {
        return ResponseEntity.internalServerError().build();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ResponseEntity internalSQLError(SQLException e) {
        return ResponseEntity.internalServerError().body("SQL Error: " + e.getErrorCode());
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity internalImplementationError(NullPointerException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
