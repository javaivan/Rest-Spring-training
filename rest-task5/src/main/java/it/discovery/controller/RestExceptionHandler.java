package it.discovery.controller;

import it.discovery.exception.BookNotFoundException;
import org.omg.CORBA.Object;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {BookNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(BookNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
