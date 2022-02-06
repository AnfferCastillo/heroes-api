package com.anffercastillo.heroes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.repositories.HeroesRepository;

public class HeroesServiceTest {

  private HeroesService heroService;

  private HeroesRepository mockHeroesRepository;

  @BeforeEach
  public void setUp() {
    mockHeroesRepository = mock(HeroesRepository.class);

    heroService = new HeroesService(mockHeroesRepository);
  }

  @Test
  public void getHeroeById_Test() {
    var id = 1L;

    when(mockHeroesRepository.getHeroeById(id)).thenReturn(Optional.of(buildDummyHero(id)));
    var heroe = heroService.getHero(id);

    assertNotNull(heroe);
    assertEquals(id, heroe.getId());
  }

  @Test
  public void getHeroeById_Not_Found_Test() {
    var id = -1L;

    when(mockHeroesRepository.getHeroeById(1L)).thenThrow(new NoSuchElementException());
    assertThrowsExactly(NoSuchElementException.class, () -> heroService.getHero(id));
  }

  @Test
  public void getAllHeroes_Test() {
    var dummyHero = buildDummyHero(1L);

    when(mockHeroesRepository.getAllHeroes()).thenReturn(List.of(dummyHero));
    List<HeroDTO> heroes = heroService.getHeroes();
    assertEquals(1, heroes.size());
  }

  @Test
  public void deleteById_Test() {
    var id = 1L;
    heroService.deleteHero(id);
    var dummyHero2 = buildDummyHero(2L);
    var dummyHero3 = buildDummyHero(3L);

    when(mockHeroesRepository.getAllHeroes()).thenReturn(List.of(dummyHero2, dummyHero3));
    var heroes = heroService.getHeroes();

    assertEquals(2, heroes.size());
    var heroe = heroes.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    assertNull(heroe);
  }

  private HeroDTO buildDummyHero(long id) {
    var dummyHero = new HeroDTO();
    dummyHero.setId(id);
    dummyHero.setName("DUMMY_NAME");
    dummyHero.setForename("ANOTHER_DUMMY_NAME");
    return dummyHero;
  }
}
