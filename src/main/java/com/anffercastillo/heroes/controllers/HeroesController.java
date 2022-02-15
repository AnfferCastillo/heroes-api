package com.anffercastillo.heroes.controllers;

import com.anffercastillo.heroes.aspects.Measure;
import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.exceptions.HeroNotFoundException;
import com.anffercastillo.heroes.services.HeroesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heroes")
@Validated
public class HeroesController {

  private HeroesService heroesService;

  public HeroesController(HeroesService heroesService) {
    this.heroesService = heroesService;
  }

  @GetMapping
  @Measure
  public SearchResponse getHeroes() {
    var heroes = heroesService.getHeroes();
    var searchResponse = SearchResponse.buildResponse(heroes);
    searchResponse.setResults(heroes);

    return searchResponse;
  }

  @GetMapping("/{id}")
  public HeroDTO getHero(@PathVariable long id) throws HeroNotFoundException {
    return heroesService.getHero(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public void deleteHero(@PathVariable long id) throws HeroNotFoundException {
    heroesService.deleteHero(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public HeroDTO updateHero(@PathVariable long id, @RequestBody HeroRequest hero) {
    return heroesService.updateHero(id, hero);
  }
}
