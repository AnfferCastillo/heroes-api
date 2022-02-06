package com.anffercastillo.heroes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.repositories.HeroesRepository;

@Service
public class HeroesService {

  private HeroesRepository heroesRepository;

  public HeroesService(HeroesRepository heroRepositoy) {
    this.heroesRepository = heroRepositoy;
  }

  public HeroDTO getHero(long id) {
    // TODO: change this to a proper exception with 404
    return heroesRepository.getHeroeById(id).orElseThrow();
  }

  public List<HeroDTO> getHeroes() {
    return heroesRepository.getAllHeroes();
  }

  public void deleteHero(long id) {
    heroesRepository.deleteHeroById(id);
  }
}
