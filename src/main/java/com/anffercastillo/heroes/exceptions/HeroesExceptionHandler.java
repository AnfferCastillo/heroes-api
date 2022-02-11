package com.anffercastillo.heroes.exceptions;

import com.anffercastillo.heroes.dto.ErrorResponse;
import com.anffercastillo.heroes.utils.MessagesConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HeroesExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(HeroesNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleHeroesExceptions(
      HeroesNotFoundException heroException, WebRequest request) {
    var errorResponse =
        new ErrorResponse(HttpStatus.NOT_FOUND.value(), MessagesConstants.HERO_NOT_FOUND);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(HeroBadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleBadRequestUpdate() {
    var errorResponse =
        new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), MessagesConstants.INVALID_HERO_UPDATE_REQUEST);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
