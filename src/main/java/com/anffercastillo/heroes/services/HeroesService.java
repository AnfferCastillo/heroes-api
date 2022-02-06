package com.anffercastillo.heroes.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.repositories.HeroesRepository;
import com.anffercastillo.heroes.utils.MessagesConstants;

@Service
public class HeroesService {

  private HeroesRepository heroesRepository;

  public HeroesService(HeroesRepository heroRepositoy) {
    this.heroesRepository = heroRepositoy;
  }

  public HeroDTO getHero(long id) {
    // TODO: change this to a proper exception with 404
    return heroesRepository
        .getHeroeById(id)
        .orElseThrow(() -> new NoSuchElementException(MessagesConstants.HERO_NOT_FOUND));
  }

  public List<HeroDTO> getHeroes() {
    return heroesRepository.getAllHeroes();
  }

  public void deleteHero(long id) {
    var hero = getHero(id);
    heroesRepository.deleteHeroById(hero.getId());
  }

  public HeroDTO updateHero(long id, HeroRequest heroUpdateRequest) {
    var currentHero = getHero(id);
    currentHero.setForename(heroUpdateRequest.getForename());
    currentHero.setName(heroUpdateRequest.getName());

    return heroesRepository.updateHero(currentHero);
  }
}
