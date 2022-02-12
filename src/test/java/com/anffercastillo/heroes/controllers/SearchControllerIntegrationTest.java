package com.anffercastillo.heroes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchControllerIntegrationTest {
  private MockMvc mockMvc;

  @Autowired private WebApplicationContext context;

  @Autowired ObjectMapper objectMapper;

  @BeforeAll
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
  }

  @Test
  @WithMockUser
  public void searchHeroesByName_Test() throws Exception {
    var name = "chapu";

    var actualResponse =
        mockMvc
            .perform(get("/search?term=" + name))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.results[0].name", is("El Chapulin")));
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
