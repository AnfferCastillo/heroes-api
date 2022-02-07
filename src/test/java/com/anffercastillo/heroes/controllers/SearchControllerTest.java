package com.anffercastillo.heroes.controllers;

import com.anffercastillo.heroes.services.HeroesService;
import com.anffercastillo.heroes.utils.HeroTestsUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchController.class)
public class SearchControllerTest {
  @Autowired private MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @MockBean private HeroesService mockHeroService;

  @Test
  public void searchHeroesByName_Test() throws Exception {
    var name = "DUMMY";
    var dummyHero = HeroTestsUtils.buildDummyHero(1L);
    var dummyHero2 = HeroTestsUtils.buildDummyHero(2L);

    var dummyHeroesList = List.of(dummyHero, dummyHero2);

    var expectedResponse = objectMapper.writeValueAsString(dummyHeroesList);
    when(mockHeroService.getHeroesByName(name)).thenReturn(dummyHeroesList);

    var actualResponse =
        mockMvc
            .perform(get("/search?term=" + name))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    verify(mockHeroService, times(1)).getHeroesByName(eq(name));
    assertEquals(expectedResponse, actualResponse);
  }
}
