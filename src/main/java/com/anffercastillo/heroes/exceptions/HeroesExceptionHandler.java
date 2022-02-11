package com.anffercastillo.heroes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.anffercastillo.heroes.dto.ErrorResponse;

@ControllerAdvice
public class HeroesExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(HeroesException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleHeroNotFoundException(
      HeroesException heroException, WebRequest request) {
    var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), heroException.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }
}
