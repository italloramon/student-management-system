package com.ramon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StudentAlreadyCreatedAdvice {

    @ResponseBody
    @ExceptionHandler(StudentAlreadyCreatedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String responsablesNotFoundHandler(StudentAlreadyCreatedException ex) {
      return ex.getMessage();
    }
}