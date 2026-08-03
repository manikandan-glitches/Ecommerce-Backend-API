package com.example.ecommerceapi.customException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(userAlreadyExistException.class)
    public ResponseEntity<responceException> userAlraedyExist(userAlreadyExistException ex){
        responceException res = new responceException("Conflict", LocalDateTime.now(), HttpStatus.CONFLICT.value(),ex.getMessage());
        return new ResponseEntity(res,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<responceException> productNotFound(ProductNotFoundException ex){
        responceException res = new responceException("NOT_FOUND", LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity(res,HttpStatus.NOT_FOUND);
    }
}
