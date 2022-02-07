package com.anffercastillo.heroes.dto;

import java.util.List;

public class SearchResponse {

  private List<HeroDTO> results;

  public List<HeroDTO> getResults() {
    return results;
  }

  public void setResults(List<HeroDTO> results) {
    this.results = results;
  }

  public static SearchResponse buildResponse(List<HeroDTO> results) {
    var searchResponse = new SearchResponse();
    searchResponse.setResults(results);
    return searchResponse;
  }
}
