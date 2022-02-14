package com.anffercastillo.heroes.controllers;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.dto.SearchResponse;
import com.anffercastillo.heroes.entities.HeroesCompany;
import com.anffercastillo.heroes.utils.MessagesConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HeroesControllerIntegrationTest {

  private static final String SOME_FORENAME = "SOME_FORENAME";

  private static final String SOME_NAME = "SOME_NAME";

  @Autowired private WebApplicationContext context;

  private MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @BeforeAll
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
  }

  @Test
  @WithMockUser
  public void getAllHeroes_Test() throws Exception {
    var responseString =
        mockMvc
            .perform(get("/heroes"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    var heroes = objectMapper.readValue(responseString, SearchResponse.class);

    assertNotNull(heroes);
    assertNotNull(heroes.getResults());
  }

  @Test
  @WithAnonymousUser
  public void getAllHeroes_Unauthorized_Test() throws Exception {
    mockMvc.perform(get("/heroes")).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser
  public void getHeroById_Test() throws Exception {
    mockMvc.perform(get("/heroes/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)));
  }

  @Test
  @WithMockUser
  public void getHeroByID_Not_Found_Test() throws Exception {
    mockMvc
        .perform(get("/heroes/32321654"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is(MessagesConstants.HERO_NOT_FOUND)));
  }

  @Test
  @WithAnonymousUser
  public void getHeroByID_Unautorized_Test() throws Exception {
    mockMvc.perform(get("/heroes/1")).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(
      username = "admin",
      roles = {"ADMIN"})
  public void deleteHeroByID_Test() throws Exception {
    var beforeString =
        mockMvc
            .perform(get("/heroes"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    mockMvc.perform(delete("/heroes/2")).andExpect(status().isOk());

    var afterString =
        mockMvc
            .perform(get("/heroes"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    var heoresBefore = objectMapper.readValue(beforeString, SearchResponse.class);
    var heoresAfter = objectMapper.readValue(afterString, SearchResponse.class);

    var deletedHero =
        heoresBefore.getResults().stream().filter(h -> h.getId() == 2).findFirst().orElse(null);
    assertNotNull(deletedHero);
    assertTrue(!heoresAfter.getResults().contains(deletedHero));

    assertEquals(heoresBefore.getResults().size(), heoresAfter.getResults().size() + 1);
    assertTrue(heoresBefore.getResults().containsAll(heoresAfter.getResults()));
  }

  @Test
  @WithMockUser(
      username = "admin",
      roles = {"ADMIN"})
  public void deleteHeroByID_NotFound_Test() throws Exception {
    mockMvc
        .perform(delete("/heroes/1321654"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is(MessagesConstants.HERO_NOT_FOUND)));
  }

  @Test
  @WithMockUser(roles = {"USER"})
  public void deleteHeroByID_Forbbiden_Test() throws Exception {
    mockMvc.perform(delete("/heroes/1")).andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(
      username = "admin",
      roles = {"ADMIN"})
  public void updateHeroById_Test() throws Exception {
    var getHeroResponse =
        mockMvc
            .perform(get("/heroes/3"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    HeroRequest heroRequest = buildHeroRequest("Cara Panetone", "Hombre Tostadas en Polvo");

    var responseString =
        mockMvc
            .perform(
                put("/heroes/3")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(heroRequest)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    var updateResponse = objectMapper.readValue(responseString, HeroDTO.class);
    assertTrue(updateResponse.getSuperPowers().isEmpty());
    assertEquals(updateResponse.getName(), "Hombre Tostadas en Polvo");
    assertEquals(updateResponse.getForename(), "Cara Panetone");
    assertEquals(updateResponse.getId(), 3);
  }

  @Test
  @WithMockUser(
      username = "admin",
      roles = {"ADMIN"})
  public void updateHeroById_Not_Found_Test() throws Exception {
    var heroRequest = buildHeroRequest(SOME_FORENAME, SOME_NAME);

    mockMvc
        .perform(
            put("/heroes/321651321")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heroRequest)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is(MessagesConstants.HERO_NOT_FOUND)));
  }

  @Test
  @WithMockUser(username = "regularUser")
  public void updateHeroById_Forbbiden_Test() throws Exception {
    mockMvc
        .perform(
            put("/heroes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsBytes(buildHeroRequest(SOME_FORENAME, SOME_NAME))))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithAnonymousUser
  public void updateHeroById_Unauthorized_test() throws Exception {
    mockMvc
        .perform(
            put("/heroes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsBytes(buildHeroRequest(SOME_FORENAME, SOME_NAME))))
        .andExpect(status().isUnauthorized());
  }

  private HeroRequest buildHeroRequest(String updated_forename, String updated_name) {
    var heroRequest = new HeroRequest();
    heroRequest.setForename(updated_forename);
    heroRequest.setName(updated_name);
    heroRequest.setCompany(HeroesCompany.MARVEL);
    heroRequest.setSuperPowers(Collections.emptyList());
    return heroRequest;
  }
}
