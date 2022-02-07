package com.anffercastillo.heroes;

import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.services.HeroesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
