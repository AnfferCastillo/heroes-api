package com.anffercastillo.heroes.repositories;

import java.util.List;
import java.util.Optional;

import com.anffercastillo.heroes.dto.HeroDTO;

public interface HeroesRepository {

  Optional<HeroDTO> findById(long id);

  List<HeroDTO> findAll();

  void deleteById(long id);

  HeroDTO save(HeroDTO hero);

  List<HeroDTO> findHeroesByName(String name);
}
