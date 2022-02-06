package com.anffercastillo.heroes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.anffercastillo.heroes.dto.HeroeDTO;

@TestInstance(Lifecycle.PER_CLASS)
public class HeroesServiceTest {

  private HeroesService heroeService;

  @BeforeAll
  public void setUp() {
    heroeService = new HeroesService();
  }

  @Test
  public void getHeroeById_Test() {
    var id = 1L;
    HeroeDTO heroe = heroeService.getHeroe(id);

    assertNotNull(heroe);
    assertEquals(id, heroe.getId());
  }

  @Test
  public void getHeroeById_Not_Found_Test() {
    var id = -1L;
    assertThrowsExactly(NoSuchElementException.class, () -> heroeService.getHeroe(id));
  }

  @Test
  public void getAllHeroes_Test() {
    List<HeroeDTO> heroes = heroeService.getHeroes();
    assertEquals(3, heroes.size());
  }

  @Test
  public void deleteTestById_Test() {
    var id = 1L;
    heroeService.deleteHeroe(id);

    var heroes = heroeService.getHeroes();

    assertEquals(2, heroes.size());
    var heroe = heroes.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    assertNull(heroe);
  }
}
