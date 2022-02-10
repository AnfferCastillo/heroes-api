package com.anffercastillo.heroes.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.services.HeroesService;
import com.anffercastillo.heroes.utils.HeroTestsUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class SearchControllerTest {
  private MockMvc mockMvc;

  @Autowired private WebApplicationContext context;

  @Autowired ObjectMapper objectMapper;

  @MockBean private HeroesService mockHeroService;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
  }

  @Test
  @WithMockUser
  public void searchHeroesByName_Test() throws Exception {
    var name = "DUMMY";
    var dummyHero = HeroTestsUtils.buildDummyHero(1L);
    var dummyHero2 = HeroTestsUtils.buildDummyHero(2L);

    var dummyHeroesList = List.of(dummyHero, dummyHero2);

    var expectedResponse =
        objectMapper.writeValueAsString(SearchResponse.buildResponse(dummyHeroesList));
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

  @Test
  @WithMockUser
  public void searchHeroesByName_Bad_Request_Test() throws Exception {
    mockMvc.perform(get("/search")).andExpect(status().isBadRequest());
    mockMvc.perform(get("/search?term=")).andExpect(status().isBadRequest());
  }

  @Test
  @WithAnonymousUser
  public void searchHeroesByName_Unauthorized_Test() throws Exception {
    mockMvc.perform(get("/search?term=test")).andExpect(status().isUnauthorized());
  }
}
