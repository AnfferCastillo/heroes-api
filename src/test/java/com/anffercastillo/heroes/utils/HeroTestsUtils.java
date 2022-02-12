package com.anffercastillo.heroes.utils;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.SuperPowerDTO;
import com.anffercastillo.heroes.entities.HeroesCompany;

import java.util.Collections;

public class HeroTestsUtils {

  public static final String DUMMY_FORENAME = "DUMMY_FORENAME_";
  public static final String DUMMY_NAME = "DUMMY_NAME_";

  public static HeroDTO buildDummyHero(long id) {
    var dummyHero = new HeroDTO();
    dummyHero.setForename(DUMMY_FORENAME + id);
    dummyHero.setName(DUMMY_NAME + id);
    dummyHero.setId(id);
    dummyHero.setCompany(HeroesCompany.MARVEL.name());
    dummyHero.setSuperPowers(Collections.emptyList());
    return dummyHero;
  }

  public static SuperPowerDTO buildDummySuperPower(long id) {
    var dummySuperPower = new SuperPowerDTO();
    dummySuperPower.setId(id);
    dummySuperPower.setName("DUMMY_SUPER_POWER_NAME_" + id);
    return dummySuperPower;
  }
}
