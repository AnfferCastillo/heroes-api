package com.anffercastillo.heroes.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.anffercastillo.heroes.dto.HeroDTO;

@Repository
public class HeroRepositoryImpl implements HeroesRepository {
  // TODO: move this to a repository.
  List<HeroDTO> heroes;

  public HeroRepositoryImpl() {
    var heroe1 = new HeroDTO();
    heroe1.setId(1L);
    heroe1.setName("Spiderman");
    heroe1.setForename("Peter Parker");

    var heroe2 = new HeroDTO();
    heroe2.setId(2L);
    heroe2.setName("Batman");
    heroe2.setForename("Bruce Wayne");

    var heroe3 = new HeroDTO();
    heroe3.setId(3L);
    heroe3.setName("Vegeta");
    heroe3.setForename("Vegeta");
    heroes = new ArrayList<HeroDTO>();

    heroes.add(heroe1);
    heroes.add(heroe2);
    heroes.add(heroe3);
  }

  @Override
  public Optional<HeroDTO> getHeroeById(long id) {
    return this.heroes.stream().filter(h -> h.getId() == id).findFirst();
  }

  @Override
  public List<HeroDTO> getAllHeroes() {
    var heroesCopy = new ArrayList<HeroDTO>();
    Collections.copy(heroes, heroesCopy);
    return heroesCopy;
  }

  @Override
  public void deleteHeroById(long id) {
    var heroe = heroes.stream().filter(h -> h.getId() == id).findFirst().orElseThrow();
    heroes.remove(heroe);
  }

  @Override
  public HeroDTO updateHero(HeroDTO any) { // TODO Auto-generated method stub
    return null;
  }
}
