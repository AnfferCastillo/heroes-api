package com.anffercastillo.heroes.services;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRequest;
import com.anffercastillo.heroes.entities.HeroesCompany;
import com.anffercastillo.heroes.exceptions.HeroBadRequestException;
import com.anffercastillo.heroes.exceptions.HeroNotFoundException;
import com.anffercastillo.heroes.repositories.HeroesRepository;
import com.anffercastillo.heroes.utils.HeroTestsUtils;
import com.anffercastillo.heroes.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.anffercastillo.heroes.utils.HeroTestsUtils.buildDummyHero;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HeroesServiceTest {

  private static final String INVALID_UPDATED_DUMMY_NAME = "INVALID_UPDATED_DUMMY_NAME";

  private static final String INVALID_UPDATED_ANOTHER_DUMMY_NAM =
      "INVALID_UPDATED_ANOTHER_DUMMY_NAM";

  private static final String UPDATED_DUMMY_NAME = "UPDATED_DUMMY_NAME";

  private static final String UPDATED_ANOTHER_DUMMY_NAM = "UPDATED_ANOTHER_DUMMY_NAM";

  private HeroesService heroService;

  private HeroesRepository mockHeroesRepository;

  @BeforeEach
  public void setUp() {
    mockHeroesRepository = mock(HeroesRepository.class);
    heroService = new HeroesService(mockHeroesRepository);
  }

  @Test
  public void getHeroeById_Test() throws HeroNotFoundException {
    var id = 1L;

    when(mockHeroesRepository.findById(id)).thenReturn(Optional.of(buildDummyHero(id)));
    var heroe = heroService.getHero(id);

    assertNotNull(heroe);
    assertEquals(id, heroe.getId());
  }

  @Test
  public void getHeroeById_Not_Found_Test() {
    var id = -1L;

    when(mockHeroesRepository.findById(id)).thenReturn(Optional.empty());
    assertThrows(
        HeroNotFoundException.class,
        () -> heroService.getHero(id),
        MessagesConstants.HERO_NOT_FOUND);
  }

  @Test
  public void getAllHeroes_Test() {
    var dummyHero = buildDummyHero(1L);

    when(mockHeroesRepository.findAll()).thenReturn(List.of(dummyHero));
    List<HeroDTO> heroes = heroService.getHeroes();
    assertEquals(1, heroes.size());
  }

  @Test
  public void deleteById_Test() throws HeroNotFoundException {
    var id = 1L;
    var dummyHero1 = buildDummyHero(1L);
    var dummyHero2 = buildDummyHero(2L);
    var dummyHero3 = buildDummyHero(3L);

    when(mockHeroesRepository.findById(id)).thenReturn(Optional.of(dummyHero1));
    when(mockHeroesRepository.findAll()).thenReturn(List.of(dummyHero2, dummyHero3));

    heroService.deleteHero(id);

    var heroes = heroService.getHeroes();

    assertEquals(2, heroes.size());
    var heroe = heroes.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    assertNull(heroe);
  }

  @Test
  public void deleteById_NotFound_Test() {
    assertThrows(
        HeroNotFoundException.class,
        () -> heroService.deleteHero(-1L),
        MessagesConstants.HERO_NOT_FOUND);
  }

  @Test
  public void updateHero_Test() throws Exception {
    var id = 1L;
    var heroUpdateRequest = new HeroRequest();
    heroUpdateRequest.setName(UPDATED_DUMMY_NAME);
    heroUpdateRequest.setForename(UPDATED_ANOTHER_DUMMY_NAM);
    heroUpdateRequest.setSuperPowers(Collections.emptyList());
    heroUpdateRequest.setCompany(HeroesCompany.MARVEL);

    var expected = new HeroDTO();
    expected.setName(UPDATED_DUMMY_NAME);
    expected.setForename(UPDATED_ANOTHER_DUMMY_NAM);
    expected.setSuperPowers(Collections.emptyList());
    expected.setCompany(HeroesCompany.MARVEL.name());
    expected.setId(id);

    var dummyHero = buildDummyHero(id);

    when(mockHeroesRepository.save(any(HeroDTO.class))).thenReturn(expected);
    when(mockHeroesRepository.findById(id)).thenReturn(Optional.of(dummyHero));

    var actual = heroService.updateHero(id, heroUpdateRequest);

    assertEquals(expected, actual);
  }

  @Test
  public void updateHero_Fail_Test() {
    var id = -1L;
    var heroUpdateRequest = new HeroRequest();
    heroUpdateRequest.setName(INVALID_UPDATED_DUMMY_NAME);
    heroUpdateRequest.setForename(INVALID_UPDATED_ANOTHER_DUMMY_NAM);

    when(mockHeroesRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(
        HeroBadRequestException.class,
        () -> heroService.updateHero(id, heroUpdateRequest),
        MessagesConstants.HERO_EMPTY_NAME_ERROR);
  }

  @Test
  public void updateHero_Fail_Invalid_Params_Test() {
    var id = 1L;
    var heroUpdateRequest = HeroTestsUtils.dummyHeroRequest(id);
    heroUpdateRequest.setName("");
    heroUpdateRequest.setForename(INVALID_UPDATED_ANOTHER_DUMMY_NAM);
    var heroDTO = HeroDTO.buildHeroDTO(1L, heroUpdateRequest);

    assertThrows(
        HeroBadRequestException.class,
        () -> heroService.updateHero(id, heroUpdateRequest),
        MessagesConstants.INVALID_HERO_UPDATE_REQUEST);

    verify(mockHeroesRepository, times(0)).findById(id);
    verify(mockHeroesRepository, times(0)).save(eq(heroDTO));
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
    assertThrows(
        HeroBadRequestException.class,
        () -> heroService.getHeroesByName(""),
        MessagesConstants.HERO_EMPTY_NAME_ERROR);
  }
}
