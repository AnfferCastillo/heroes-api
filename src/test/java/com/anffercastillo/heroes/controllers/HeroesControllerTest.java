package com.anffercastillo.heroes.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.anffercastillo.heroes.HeroesController;
import com.anffercastillo.heroes.services.HeroesService;

@WebMvcTest(HeroesController.class)
public class HeroesControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private HeroesService mockHeroService;

  @Test
  public void getAllHeroes_Test() throws Exception {
    when(mockHeroService.getHeroes()).thenReturn(Collections.EMPTY_LIST);
    mockMvc.perform(get("/heroes")).andExpect(status().isOk());
  }
}
