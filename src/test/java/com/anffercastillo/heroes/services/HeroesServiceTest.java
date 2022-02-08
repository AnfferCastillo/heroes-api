package com.anffercastillo.heroes.services;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.entities.Heroes;
import com.anffercastillo.heroes.repositories.HeroesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    when(mockHeroesRepository.findHeroesById(id)).thenReturn(Optional.of(buildDummyHero(id)));
    var heroe = heroService.getHero(id);

    assertNotNull(heroe);
    assertEquals(id, heroe.getId());
  }

  @Test
  public void getHeroeById_Not_Found_Test() {
    var id = -1L;

    when(mockHeroesRepository.findHeroesById(id)).thenThrow(new NoSuchElementException());
    assertThrowsExactly(NoSuchElementException.class, () -> heroService.getHero(id));
  }

  @Test
  public void getAllHeroes_Test() {
    var dummyHero = buildDummyHero(1L);

    when(mockHeroesRepository.findAll()).thenReturn(List.of(dummyHero));
    List<HeroDTO> heroes = heroService.getHeroes();
    assertEquals(1, heroes.size());
  }

  @Test
  public void deleteById_Test() {
    var id = 1L;
    var dummyHero1 = buildDummyHero(1L);
    var dummyHero2 = buildDummyHero(2L);
    var dummyHero3 = buildDummyHero(3L);

    when(mockHeroesRepository.findHeroesById(id)).thenReturn(Optional.of(dummyHero1));
    when(mockHeroesRepository.findAll()).thenReturn(List.of(dummyHero2, dummyHero3));

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
  public void updateHero_Test() throws Exception {

    var id = 1L;
    var heroUpdateRequest = new HeroRequest();
    heroUpdateRequest.setName("UPDATED_DUMMY_NAME");
    heroUpdateRequest.setForename("UPDATED_ANOTHER_DUMMY_NAM");

    var expected = new Heroes();
    expected.setName("UPDATED_DUMMY_NAME");
    expected.setForename("UPDATED_ANOTHER_DUMMY_NAM");
    expected.setId(1L);

    var dummyHero = buildDummyHero(id);

    when(mockHeroesRepository.save(any(Heroes.class))).thenReturn(expected);
    when(mockHeroesRepository.findHeroesById(id)).thenReturn(Optional.of(dummyHero));

    var actual = heroService.updateHero(id, heroUpdateRequest);

    assertEquals(expected, actual);
  }

  @Test
  public void updateHero_Fail_Test() {
    var id = -1L;
    var heroUpdateRequest = new HeroRequest();
    heroUpdateRequest.setName("INVALID_UPDATED_DUMMY_NAME");
    heroUpdateRequest.setForename("INVALID_UPDATED_ANOTHER_DUMMY_NAM");

    when(mockHeroesRepository.findHeroesById(id)).thenThrow(new NoSuchElementException());

    assertThrows(NoSuchElementException.class, () -> heroService.updateHero(id, heroUpdateRequest));
  }

  @Test
  public void updateHero_Fail_Invalid_Params_Test() {
    var id = -1L;
    var heroUpdateRequest = new HeroRequest();
    heroUpdateRequest.setName("");
    heroUpdateRequest.setForename("INVALID_UPDATED_ANOTHER_DUMMY_NAM");

    when(mockHeroesRepository.findHeroesById(id)).thenThrow(new NoSuchElementException());

    assertThrows(Exception.class, () -> heroService.updateHero(id, heroUpdateRequest));
  }

  @Test
  public void getHeroesByName_Test() throws Exception {
    var dummyHero1 = buildDummyHero(1L);
    var dummyHero2 = buildDummyHero(2L);
    var dummyHero3 = buildDummyHero(3L);

    when(mockHeroesRepository.findHeroesByName("DUMMY"))
        .thenReturn(List.of(dummyHero1, dummyHero2, dummyHero3));

    List<HeroDTO> heroes = heroService.getHeroesByName("DUMMY");

    assertEquals(3, heroes.size());
    assertEquals(dummyHero1.getName(), heroes.get(0).getName());
    assertEquals(dummyHero2.getName(), heroes.get(1).getName());
    assertEquals(dummyHero3.getName(), heroes.get(2).getName());
  }

  @Test
  public void getHeroesByName_Fail_Test() {
    assertThrows(Exception.class, () -> heroService.getHeroesByName(""));
  }

  private Heroes buildDummyHero(long id) {
    var dummyHero = new Heroes();
    dummyHero.setId(id);
    dummyHero.setName("DUMMY_NAME_" + id);
    dummyHero.setForename("ANOTHER_DUMMY_NAME_" + id);
    return dummyHero;
  }
}
