package com.anffercastillo.heroes.controllers;

import com.anffercastillo.heroes.HeroesController;
import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.services.HeroesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeroesController.class)
public class HeroesControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @MockBean private HeroesService mockHeroService;

  @Test
  public void getAllHeroes_Test() throws Exception {
    var dummyHero = new HeroDTO();
    dummyHero.setName("hero 1");
    dummyHero.setForename("hero 1");
    dummyHero.setId(1L);

    var dummyHero2 = new HeroDTO();
    dummyHero2.setName("hero 2");
    dummyHero2.setForename("hero 2");
    dummyHero2.setId(2L);

    var results = List.of(dummyHero, dummyHero2);
    var expectedResponse = new SearchResponse();
    expectedResponse.setResults(List.of(dummyHero, dummyHero2));
    when(mockHeroService.getHeroes()).thenReturn(results);

    var actualResponse =
        mockMvc
            .perform(get("/heroes"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    assertEquals(actualResponse, objectMapper.writeValueAsString(expectedResponse));
  }
}
