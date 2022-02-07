package com.anffercastillo.heroes.utils;

import com.anffercastillo.heroes.dto.HeroDTO;

public class HeroTestsUtils {

  public static HeroDTO buildDummyHero(long id) {
    var dummyHero = new HeroDTO();
    dummyHero.setForename("DUMMY_FORENAME_" + id);
    dummyHero.setName("DUMMY_NAME_" + id);
    dummyHero.setId(id);
    return dummyHero;
  }
}
