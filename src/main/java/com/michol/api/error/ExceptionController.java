package com.michol.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorData> handleNotValidException(MethodArgumentNotValidException e) {
        ErrorBuilder builder = new ErrorBuilder().message("Validation error");
        e.getBindingResult().getFieldErrors().forEach(fieldError -> builder.error(fieldError.getDefaultMessage()));
        return new ResponseEntity<>(builder.build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoResultException.class)
    public ResponseEntity<ErrorData> handleNotValidException(NoResultException e) {
        ErrorBuilder builder = new ErrorBuilder().message("No user found");
        return new ResponseEntity<>(builder.build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value =Exception.class)
    public ResponseEntity<ErrorData> handleOtherException(Exception e) {
        return new ResponseEntity<>(new ErrorBuilder().message("Other error").error(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
