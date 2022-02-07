package com.anffercastillo.heroes.controllers;

import com.anffercastillo.heroes.HeroesController;
import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.services.HeroesService;
import com.anffercastillo.heroes.utils.HeroTestsUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeroesController.class)
public class HeroesControllerTest {

  public static final long ID = 1L;

  @Autowired private MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @MockBean private HeroesService mockHeroService;

  @Test
  public void getAllHeroes_Test() throws Exception {
    var dummyHero = HeroTestsUtils.buildDummyHero(ID);
    var dummyHero2 = HeroTestsUtils.buildDummyHero(2L);

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

  @Test
  public void getHeroById_Test() throws Exception {
    var dummyHero = HeroTestsUtils.buildDummyHero(ID);

    when(mockHeroService.getHero(ID)).thenReturn(dummyHero);
    var actualResponse =
        mockMvc
            .perform(get("/heroes/" + ID))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
    assertEquals(actualResponse, objectMapper.writeValueAsString(dummyHero));
  }

  @Test
  public void getHeroByID_Not_Found_Test() throws Exception {
    when(mockHeroService.getHero(ID)).thenThrow(new NoSuchElementException());

    mockMvc.perform(get("/heroes/" + ID)).andExpect(status().isNotFound());
  }

  @Test
  public void deleteHeroByID_Test() throws Exception {
    mockMvc.perform(delete("/heroes/" + ID)).andExpect(status().isOk());
  }

  @Test
  public void deleteHeroByID_NotFound_Test() throws Exception {
    doThrow(new NoSuchElementException()).when(mockHeroService).deleteHero(ID);

    mockMvc.perform(delete("/heroes/" + ID)).andExpect(status().isNotFound());
  }
}
