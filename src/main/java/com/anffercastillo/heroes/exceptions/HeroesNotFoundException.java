package com.anffercastillo.heroes.exceptions;

public class HeroesNotFoundException extends Exception {

  public HeroesNotFoundException() {
    super();
  }

  public HeroesNotFoundException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public HeroesNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public HeroesNotFoundException(String message) {
    super(message);
  }

  public HeroesNotFoundException(Throwable cause) {
    super(cause);
  }
}
