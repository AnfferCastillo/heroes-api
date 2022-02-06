package com.anffercastillo.heroes.repositories;

import java.util.List;
import java.util.Optional;

import com.anffercastillo.heroes.dto.HeroDTO;

public interface HeroesRepository {

  Optional<HeroDTO> getHeroeById(long id);

  List<HeroDTO> getAllHeroes();

  void deleteHeroById(long id);

  HeroDTO updateHero(HeroDTO hero);

  List<HeroDTO> getHeroesByName(String string);
}
