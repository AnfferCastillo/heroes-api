package com.anffercastillo.heroes.controllers;

import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.services.HeroesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

  private HeroesService heroesService;

  public SearchController(HeroesService heroesService) {
    this.heroesService = heroesService;
  }

  @GetMapping
  public SearchResponse getHeroesByName(@RequestParam(required = true) String term) {
    var results = heroesService.getHeroesByName(term);
    return SearchResponse.buildResponse(results);
  }
}
