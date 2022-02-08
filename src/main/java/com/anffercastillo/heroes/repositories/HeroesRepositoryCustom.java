package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.entities.Hero;

import java.util.List;

public interface HeroesRepositoryCustom {

  List<Hero> findHeroesByName(String name);
}
