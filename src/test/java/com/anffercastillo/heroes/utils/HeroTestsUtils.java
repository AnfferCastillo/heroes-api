package com.anffercastillo.heroes.utils;

import com.anffercastillo.heroes.dto.HeroDTO;

public class HeroTestsUtils {

  public static final String DUMMY_FORENAME = "DUMMY_FORENAME_";
  public static final String DUMMY_NAME = "DUMMY_NAME_";

  public static HeroDTO buildDummyHero(long id) {
    var dummyHero = new HeroDTO();
    dummyHero.setForename(DUMMY_FORENAME + id);
    dummyHero.setName(DUMMY_NAME + id);
    dummyHero.setId(id);
    return dummyHero;
  }
}
