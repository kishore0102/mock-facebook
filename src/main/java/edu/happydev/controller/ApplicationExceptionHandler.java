package edu.happydev.controller;

import edu.happydev.model.Error;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(ApplicationExceptionHandler.class.getName());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        LOGGER.severe("Exception - " + ex.getMessage() + " - ");
        ex.printStackTrace();
        Error error = new Error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
