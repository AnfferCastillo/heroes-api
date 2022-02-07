package com.anffercastillo.heroes.controllers;

import com.anffercastillo.heroes.HeroesController;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.services.HeroesService;
import com.anffercastillo.heroes.utils.HeroTestsUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    verify(mockHeroService, times(1)).getHeroes();
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

    verify(mockHeroService, times(1)).getHero(ID);
    assertEquals(actualResponse, objectMapper.writeValueAsString(dummyHero));
  }

  @Test
  public void getHeroByID_Not_Found_Test() throws Exception {
    when(mockHeroService.getHero(ID)).thenThrow(new NoSuchElementException());

    mockMvc.perform(get("/heroes/" + ID)).andExpect(status().isNotFound());

    verify(mockHeroService, times(1)).getHero(ID);
  }

  @Test
  public void deleteHeroByID_Test() throws Exception {
    mockMvc.perform(delete("/heroes/" + ID)).andExpect(status().isOk());
    verify(mockHeroService, times(1)).deleteHero(1L);
  }

  @Test
  public void deleteHeroByID_NotFound_Test() throws Exception {
    doThrow(new NoSuchElementException()).when(mockHeroService).deleteHero(ID);

    mockMvc.perform(delete("/heroes/" + ID)).andExpect(status().isNotFound());
    verify(mockHeroService, times(1)).deleteHero(ID);
  }

  @Test
  public void updateHeroById_Test() throws Exception {
    var updated_forename = "UPDATED_FORENAME";
    var updated_name = "UPDATED_NAME";

    var dummyHero = HeroTestsUtils.buildDummyHero(ID);
    dummyHero.setForename(updated_forename);
    dummyHero.setName(updated_name);

    HeroRequest heroRequest = buildHeroRequest(updated_forename, updated_name);

    var expectedResonse = objectMapper.writeValueAsString(dummyHero);
    when(mockHeroService.updateHero(eq(ID), any())).thenReturn(dummyHero);

    var actualResponse =
        mockMvc
            .perform(
                put("/heroes/" + ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(heroRequest)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    verify(mockHeroService, times(1)).updateHero(eq(ID), any());
    assertEquals(expectedResonse, actualResponse);
  }

  @Test
  public void updateHeroById_Not_Found_Test() throws Exception {
    var heroRequest = buildHeroRequest("SOME_FORENAME", "SOME_NAME");
    when(mockHeroService.updateHero(eq(ID), any())).thenThrow(new NoSuchElementException());

    mockMvc
        .perform(
            put("/heroes/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heroRequest)))
        .andExpect(status().isNotFound());

    verify(mockHeroService, times(1)).updateHero(eq(ID), any());
  }

  private HeroRequest buildHeroRequest(String updated_forename, String updated_name) {
    var heroRequest = new HeroRequest();
    heroRequest.setForename(updated_forename);
    heroRequest.setName(updated_name);
    return heroRequest;
  }
}
