package com.anffercastillo.heroes;

public class HeroesException extends Exception {

  public HeroesException() {
    super();
  }

  public HeroesException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public HeroesException(String message, Throwable cause) {
    super(message, cause);
  }

  public HeroesException(String message) {
    super(message);
  }

  public HeroesException(Throwable cause) {
    super(cause);
  }
}
