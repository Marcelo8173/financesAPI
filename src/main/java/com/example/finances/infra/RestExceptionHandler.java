package com.example.finances.infra;

import com.example.finances.infra.standarError.StandardError;
import com.example.finances.utils.exceptions.AlreadyExists;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.UnexpectedTypeException;
import org.hibernate.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NonUniqueResultException.class)
    private ResponseEntity<String> uniqueResultExceotion (NonUniqueResultException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not available");
    }

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExists ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}