package com.anffercastillo.heroes.controllers;

import com.anffercastillo.heroes.aspects.Measure;
import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.exceptions.HeroesNotFoundException;
import com.anffercastillo.heroes.services.HeroesService;
import com.anffercastillo.heroes.utils.MessagesConstants;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/heroes")
public class HeroesController {

  private HeroesService heroesService;

  public HeroesController(HeroesService heroesService) {
    this.heroesService = heroesService;
  }

  @GetMapping
  @Measure
  public SearchResponse getHeroes() {
    var heroes = heroesService.getHeroes();
    // TODO: move to a factory method
    var searchResponse = new SearchResponse();
    searchResponse.setResults(heroes);

    return searchResponse;
  }

  @GetMapping("/{id}")
  public HeroDTO getHero(@PathVariable long id) throws HeroesNotFoundException {
    var hero = heroesService.getHero(id);
    return hero;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public void deleteHero(@PathVariable long id) throws HeroesNotFoundException {
    // FIXME: change this after exception handling is implmented
    try {
      heroesService.deleteHero(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessagesConstants.HERO_NOT_FOUND);
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public HeroDTO updateHero(@PathVariable long id, @RequestBody HeroRequest hero) throws Exception {
    // FIXME: change this after exception handling is implmented
    try {
      return heroesService.updateHero(id, hero);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessagesConstants.HERO_NOT_FOUND);
    }
  }
}
