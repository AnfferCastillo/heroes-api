package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.entities.Heroes;

import java.util.List;

public interface HeroesRepositoryCustom {

  List<Heroes> findHeroesByName(String name);
}
