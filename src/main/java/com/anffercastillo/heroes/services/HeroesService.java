package com.anffercastillo.heroes.services;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.exceptions.HeroBadRequestException;
import com.anffercastillo.heroes.exceptions.HeroNotFoundException;
import com.anffercastillo.heroes.repositories.HeroesRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class HeroesService {

  private HeroesRepository heroesRepository;

  public HeroesService(HeroesRepository heroRepositoy) {
    this.heroesRepository = heroRepositoy;
  }

  @Cacheable(value = "heroes", key = "#a0")
  public HeroDTO getHero(long id) {
    return heroesRepository.findById(id).orElseThrow(HeroNotFoundException::new);
  }

  @Cacheable("search")
  public List<HeroDTO> getHeroes() {
    return heroesRepository.findAll();
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
  public HeroDTO updateHero(long id, HeroRequest heroUpdateRequest) {
    validateHeroRequest(heroUpdateRequest);

    var currentHero = HeroDTO.buildHeroDTO(id, heroUpdateRequest);
    heroesRepository.save(currentHero);
    return currentHero;
  }

  @Cacheable(value = "search", key = "#a0")
  public List<HeroDTO> getHeroesByName(String name) {
    if (!StringUtils.hasLength(name)) {
      throw new HeroBadRequestException();
    }
    return heroesRepository.findHeroesByName(name);
  }

  private void validateHeroRequest(HeroRequest heroUpdateRequest) {
    var isInvalid =
        !StringUtils.hasLength(heroUpdateRequest.getName())
            || !StringUtils.hasLength(heroUpdateRequest.getForename())
            || (heroUpdateRequest.getSuperPowers() == null)
            || (heroUpdateRequest.getCompany() == null);

    if (isInvalid) {
      throw new HeroBadRequestException();
    }
  }
}
