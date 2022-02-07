package com.anffercastillo.heroes;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.services.HeroesService;
import com.anffercastillo.heroes.utils.MessagesConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
  public SearchResponse getHeroes() {
    var heroes = heroesService.getHeroes();
    // TODO: move to a factory method
    var searchResponse = new SearchResponse();
    searchResponse.setResults(heroes);

    return searchResponse;
  }

  @GetMapping("/{id}")
  public HeroDTO getHero(@PathVariable long id) {
    // FIXME: change this after exception handling is implmented
    try {
      var hero = heroesService.getHero(id);
      return hero;
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessagesConstants.HERO_NOT_FOUND);
    }
  }
}
