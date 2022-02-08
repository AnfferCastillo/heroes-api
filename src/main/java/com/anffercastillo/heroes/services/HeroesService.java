package com.anffercastillo.heroes.services;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.repositories.HeroesRepository;
import com.anffercastillo.heroes.utils.MessagesConstants;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class HeroesService {

  private HeroesRepository heroesRepository;

  public HeroesService(HeroesRepository heroRepositoy) {
    this.heroesRepository = heroRepositoy;
  }

  public HeroDTO getHero(long id) {
    // TODO: change this to a proper exception with 404
    return heroesRepository
        .findHeroesById(id)
        .map(HeroDTO::buildHeroDTO)
        .orElseThrow(() -> new NoSuchElementException(MessagesConstants.HERO_NOT_FOUND));
  }

  public List<HeroDTO> getHeroes() {
    return heroesRepository.findAll().stream()
        .map(HeroDTO::buildHeroDTO)
        .collect(Collectors.toList());
  }

  public void deleteHero(long id) {
    var hero = getHero(id);
    heroesRepository.deleteById(hero.getId());
  }

  public HeroDTO updateHero(long id, HeroRequest heroUpdateRequest) throws Exception {
    if (!StringUtils.hasLength(heroUpdateRequest.getName())) {
      // TODO: Change for HeroException later
      throw new Exception(MessagesConstants.HERO_EMPTY_NAME_ERROR);
    }

    if (!StringUtils.hasLength(heroUpdateRequest.getForename())) {
      // TODO: Change for HeroException later
      throw new Exception(MessagesConstants.HERO_EMPTY_FORENAME_ERROR);
    }

    var currentHero = getHero(id);

    currentHero.setForename(heroUpdateRequest.getForename());
    currentHero.setName(heroUpdateRequest.getName());

    return heroesRepository.updateHero(currentHero);
  }

  public List<HeroDTO> getHeroesByName(String name) throws Exception {
    if (!StringUtils.hasLength(name)) {
      // TODO: Change for HeroException later
      throw new Exception(MessagesConstants.HERO_EMPTY_NAME_ERROR);
    }
    return heroesRepository.findHeroesByName(name).stream()
        .map(HeroDTO::buildHeroDTO)
        .collect(Collectors.toList());
  }
}
