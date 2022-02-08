package com.anffercastillo.heroes.utils;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.entities.Hero;

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

  public static Hero buildDummyHeroEntity(long id) {
    var dummyHero = new Hero();
    dummyHero.setId(id);
    dummyHero.setName("DUMMY_NAME_" + id);
    dummyHero.setForename("ANOTHER_DUMMY_NAME_" + id);
    return dummyHero;
  }
}
