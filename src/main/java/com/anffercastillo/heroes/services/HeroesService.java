package com.anffercastillo.heroes.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

  @Cacheable(value = "heroes", key = "#a0")
  public HeroDTO getHero(long id) {
    return heroesRepository
        .findHeroesById(id)
        .map(HeroDTO::buildHeroDTO)
        .orElseThrow(() -> new NoSuchElementException(MessagesConstants.HERO_NOT_FOUND));
  }

  @Cacheable("search")
  public List<HeroDTO> getHeroes() {
    return heroesRepository.findAll().stream()
        .map(HeroDTO::buildHeroDTO)
        .collect(Collectors.toList());
  }

  @Caching(
      evict = {
        @CacheEvict(value = "heores", key = "#a0"),
        @CacheEvict(value = "search", allEntries = true)
      })
  public void deleteHero(long id) {
    var hero = getHero(id);
    heroesRepository.deleteById(hero.getId());
  }

  @Caching(
      evict = {
        @CacheEvict(value = "heroes", key = "#a0"),
        @CacheEvict(value = "search", allEntries = true)
      })
  public HeroDTO updateHero(long id, HeroRequest heroUpdateRequest) throws Exception {
    if (!StringUtils.hasLength(heroUpdateRequest.getName())) {
      // TODO: Change for HeroException later
      throw new Exception(MessagesConstants.HERO_EMPTY_NAME_ERROR);
    }

    if (!StringUtils.hasLength(heroUpdateRequest.getForename())) {
      // TODO: Change for HeroException later
      throw new Exception(MessagesConstants.HERO_EMPTY_FORENAME_ERROR);
    }

    var currentHero =
        heroesRepository
            .findHeroesById(id)
            .orElseThrow(() -> new NoSuchElementException(MessagesConstants.HERO_NOT_FOUND));

    currentHero.setForename(heroUpdateRequest.getForename());
    currentHero.setName(heroUpdateRequest.getName());

    var updatedHero = heroesRepository.save(currentHero);

    return HeroDTO.buildHeroDTO(updatedHero);
  }

  @Cacheable(value = "search", key = "#a0")
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
