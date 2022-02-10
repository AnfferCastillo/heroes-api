package com.anffercastillo.heroes.services;

import static com.anffercastillo.heroes.utils.HeroTestsUtils.buildDummyHeroEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.anffercastillo.heroes.HeroesException;
import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.entities.Hero;
import com.anffercastillo.heroes.entities.HeroesCompany;
import com.anffercastillo.heroes.repositories.HeroesRepository;
import com.anffercastillo.heroes.utils.MessagesConstants;

public class HeroesServiceTest {

  private HeroesService heroService;

  private HeroesRepository mockHeroesRepository;

  @BeforeEach
  public void setUp() {
    mockHeroesRepository = mock(HeroesRepository.class);
    heroService = new HeroesService(mockHeroesRepository);
  }

  @Test
  public void getHeroeById_Test() throws HeroesException {
    var id = 1L;

    when(mockHeroesRepository.findHeroesById(id)).thenReturn(Optional.of(buildDummyHeroEntity(id)));
    var heroe = heroService.getHero(id);

    assertNotNull(heroe);
    assertEquals(id, heroe.getId());
  }

  @Test
  public void getHeroeById_Not_Found_Test() {
    var id = -1L;

    when(mockHeroesRepository.findHeroesById(id)).thenReturn(Optional.empty());
    assertThrows(
        HeroesException.class, () -> heroService.getHero(id), MessagesConstants.HERO_NOT_FOUND);
  }

  @Test
  public void getAllHeroes_Test() {
    var dummyHero = buildDummyHeroEntity(1L);

    when(mockHeroesRepository.findAll()).thenReturn(List.of(dummyHero));
    List<HeroDTO> heroes = heroService.getHeroes();
    assertEquals(1, heroes.size());
  }

  @Test
  public void deleteById_Test() {
    var id = 1L;
    var dummyHero1 = buildDummyHeroEntity(1L);
    var dummyHero2 = buildDummyHeroEntity(2L);
    var dummyHero3 = buildDummyHeroEntity(3L);

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
    heroUpdateRequest.setSuperPowers(Collections.emptyList());
    heroUpdateRequest.setCompany(HeroesCompany.MARVEL);

    var expected = new Hero();
    expected.setName("UPDATED_DUMMY_NAME");
    expected.setForename("UPDATED_ANOTHER_DUMMY_NAM");
    expected.setSuperPowers(Collections.emptyList());
    expected.setCompany(HeroesCompany.MARVEL);
    expected.setId(1L);

    var dummyHero = buildDummyHeroEntity(id);

    when(mockHeroesRepository.save(any(Hero.class))).thenReturn(expected);
    when(mockHeroesRepository.findHeroesById(id)).thenReturn(Optional.of(dummyHero));

    var actual = heroService.updateHero(id, heroUpdateRequest);

    assertEquals(HeroDTO.buildHeroDTO(expected), actual);
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
    var dummyHero1 = buildDummyHeroEntity(1L);
    var dummyHero2 = buildDummyHeroEntity(2L);
    var dummyHero3 = buildDummyHeroEntity(3L);

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
}
