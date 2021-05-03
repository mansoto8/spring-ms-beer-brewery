package com.ms.springmsbeerbrewery.web.controller;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler
{
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
    List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

    e.getConstraintViolations().forEach(constraintViolation -> {
      errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
    });

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity handleBindException(BindException e) {
    return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_REQUEST);
  }
}
