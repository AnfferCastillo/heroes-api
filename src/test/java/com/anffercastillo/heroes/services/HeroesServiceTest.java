package com.anffercastillo.heroes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
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
    var dummyHero1 = buildDummyHero(1L);
    var dummyHero2 = buildDummyHero(2L);
    var dummyHero3 = buildDummyHero(3L);

    when(mockHeroesRepository.getHeroeById(id)).thenReturn(Optional.of(dummyHero1));
    when(mockHeroesRepository.getAllHeroes()).thenReturn(List.of(dummyHero2, dummyHero3));

    heroService.deleteHero(id);

    var heroes = heroService.getHeroes();

    assertEquals(2, heroes.size());
    var heroe = heroes.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    assertNull(heroe);
  }

  @Test
  public void deleteById_Fail_Test() {
    assertThrows(NoSuchElementException.class, () -> heroService.deleteHero(-1L));
  }

  @Test
  public void updateHero_Test() {

    var id = 1L;
    var heroUpdateRequest = new HeroRequest();
    heroUpdateRequest.setName("UPDATED_DUMMY_NAME");
    heroUpdateRequest.setForename("UPDATED_ANOTHER_DUMMY_NAM");

    var expected = new HeroDTO();
    expected.setName("UPDATED_DUMMY_NAME");
    expected.setForename("UPDATED_ANOTHER_DUMMY_NAM");
    expected.setId(1L);

    var dummyHero = buildDummyHero(id);

    when(mockHeroesRepository.updateHero(any(HeroDTO.class))).thenReturn(expected);
    when(mockHeroesRepository.getHeroeById(id)).thenReturn(Optional.of(dummyHero));

    var actual = heroService.updateHero(id, heroUpdateRequest);

    assertEquals(expected, actual);
  }

  private HeroDTO buildDummyHero(long id) {
    var dummyHero = new HeroDTO();
    dummyHero.setId(id);
    dummyHero.setName("DUMMY_NAME");
    dummyHero.setForename("ANOTHER_DUMMY_NAME");
    return dummyHero;
  }
}
