package com.itihas.crudSpringBootDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleStudentAlreadyExists(
            StudentAlreadyExistException ex) {

        Map<String, Object> response = Map.of(
                "status", 409,
                "message", ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
}
