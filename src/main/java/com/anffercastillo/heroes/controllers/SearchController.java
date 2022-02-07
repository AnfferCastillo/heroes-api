package com.anffercastillo.heroes.controllers;

import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.services.HeroesService;
import com.anffercastillo.heroes.utils.MessagesConstants;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/search")
public class SearchController {

  private HeroesService heroesService;

  public SearchController(HeroesService heroesService) {
    this.heroesService = heroesService;
  }

  @GetMapping
  public SearchResponse getHeroesByName(@RequestParam(required = true) String term)
      throws Exception {
    if (!StringUtils.hasLength(term)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, MessagesConstants.HERO_EMPTY_NAME_ERROR);
    }
    var results = heroesService.getHeroesByName(term);
    return SearchResponse.buildResponse(results);
  }
}
