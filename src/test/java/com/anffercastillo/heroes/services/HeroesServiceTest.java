package com.anffercastillo.heroes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import com.anffercastillo.heroes.dto.HeroeDTO;

public class HeroesServiceTest {

  private HeroesService heroeService;

  @Test
  public void getHeroeById_Test() {
    heroeService = new HeroesService();

    var id = 1L;

    HeroeDTO heroe = heroeService.getHeroe(id);

    assertNotNull(heroe);
    assertEquals(id, heroe.getId());
  }

  @Test
  public void getHeroeById_Not_Found_Test() {
    heroeService = new HeroesService();

    var id = -1L;

    assertThrowsExactly(NoSuchElementException.class, () -> heroeService.getHeroe(id));
  }
}
