package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.dto.HeroRowMapper;
import com.anffercastillo.heroes.exceptions.HeroesNotFoundException;
import com.anffercastillo.heroes.utils.HeroTestsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class HeroesRepositoryImplTest {

  private static final long ID = 1L;

  private HeroesRepositoryImpl heroesRepository;

  private NamedParameterJdbcTemplate mockJdbcTemplate;

  private SuperPowerRepository mockSuperPowersRepository;

  @BeforeEach
  public void setup() {
    mockJdbcTemplate = mock(NamedParameterJdbcTemplate.class);
    mockSuperPowersRepository = mock(SuperPowerRepository.class);

    heroesRepository = new HeroesRepositoryImpl(mockJdbcTemplate, mockSuperPowersRepository);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void findHeroesByName_Test() {
    var name = "DUMMY";

    var dummyHero = HeroTestsUtils.buildDummyHero(ID);
    var dummyHero2 = HeroTestsUtils.buildDummyHero(2L);

    var expected = List.of(dummyHero, dummyHero2);

    when(mockJdbcTemplate.query(
            Mockito.eq(HeroesRepositoryImpl.QUERY_BY_NAME),
            Mockito.any(Map.class),
            Mockito.any(HeroRowMapper.class)))
        .thenReturn(expected);

    var actual = heroesRepository.findHeroesByName(name);

    assertEquals(expected.size(), actual.size());
    assertEquals(expected.get(0), dummyHero);
    assertEquals(expected.get(1), dummyHero2);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void findHeroById_Test() {
    var expected = HeroTestsUtils.buildDummyHero(ID);
    when(mockJdbcTemplate.queryForObject(
            Mockito.eq(HeroesRepositoryImpl.QUERY_BY_ID),
            Mockito.any(Map.class),
            Mockito.any(HeroRowMapper.class)))
        .thenReturn(expected);

    var actual = heroesRepository.findById(ID).get();
    assertEquals(expected, actual);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void findHeroById_No_Results_Test() {
    when(mockJdbcTemplate.queryForObject(
            eq(HeroesRepositoryImpl.QUERY_BY_ID), any(Map.class), any(HeroRowMapper.class)))
        .thenReturn(null);

    var actual = heroesRepository.findById(ID);
    assertTrue(actual.isEmpty());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void deleteById_Test() {
    when(mockJdbcTemplate.update(eq(HeroesRepositoryImpl.DELETE_BY_ID), any(Map.class)))
        .thenReturn(1);

    heroesRepository.deleteById(ID);
    verify(mockJdbcTemplate, times(1)).update(any(), any(Map.class));
  }

  @Test
  public void deleteById_Not_Found_Test() {
    when(mockJdbcTemplate.update(eq(HeroesRepositoryImpl.QUERY_BY_ID), any(Map.class)))
        .thenThrow(new HeroesNotFoundException());

    assertThrows(HeroesNotFoundException.class, () -> heroesRepository.deleteById(ID));
    verify(mockJdbcTemplate, times(1)).update(any(), any(Map.class));
  }

  @Test
  public void saveHero_Test() {
    var expected = HeroTestsUtils.buildDummyHero(ID);
    expected.setName("UPDATED_NAME");
    expected.setSuperPowers(List.of(HeroTestsUtils.buildDummySuperPower(1L)));

    when(mockJdbcTemplate.query(eq(HeroesRepositoryImpl.GET_NEXT_ID), any(RowMapper.class)))
        .thenReturn(List.of(ID));
    when(mockJdbcTemplate.update(eq(HeroesRepositoryImpl.SAVE_HERO), any(Map.class))).thenReturn(1);
    when(mockSuperPowersRepository.updateHeroSuperPowers(eq(ID), any()))
        .thenReturn(List.of(HeroTestsUtils.buildDummySuperPower(1L)));

    var actual = heroesRepository.save(expected);
    verify(mockJdbcTemplate, times(1)).update(any(), any(Map.class));
    verify(mockSuperPowersRepository, times(1)).updateHeroSuperPowers(eq(ID), any());
    assertEquals(expected, actual);
  }
}
