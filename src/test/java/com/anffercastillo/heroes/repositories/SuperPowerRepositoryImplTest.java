package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.exceptions.HeroBadRequestException;
import com.anffercastillo.heroes.utils.HeroTestsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SuperPowerRepositoryImplTest {

  private SuperPowerRepository superPowerRepository;
  private NamedParameterJdbcTemplate mockJdbcTemplate;

  @BeforeEach
  public void setup() {
    mockJdbcTemplate = mock(NamedParameterJdbcTemplate.class);
    superPowerRepository = new SuperPowerRepositoryImpl(mockJdbcTemplate);
  }

  @Test
  public void findSuperPowersByHeroId_Test() {
    var expected = List.of(HeroTestsUtils.buildDummySuperPower(1L));
    when(mockJdbcTemplate.query(
            eq(SuperPowerRepositoryImpl.QUERY_BY_HERO_ID), any(Map.class), any(RowMapper.class)))
        .thenReturn(expected);

    var actual = superPowerRepository.findSuperPowerByHeroId(1L);

    verify(mockJdbcTemplate, times(1))
        .query(eq(SuperPowerRepositoryImpl.QUERY_BY_HERO_ID), any(Map.class), any(RowMapper.class));
    assertEquals(expected.size(), actual.size());
    assertEquals(expected.get(0), actual.get(0));
  }

  @Test
  public void updateHeroesSuperPowers_Test() {
    var dummySuperPower = HeroTestsUtils.buildDummySuperPower(1L);
    var dummySuperPower2 = HeroTestsUtils.buildDummySuperPower(2L);
    var expected = List.of(dummySuperPower, dummySuperPower2);
    var heroId = 1L;
    var superPowers = List.of(dummySuperPower, dummySuperPower2);

    when(mockJdbcTemplate.queryForObject(
            eq(SuperPowerRepositoryImpl.COUNT_SUPER_POWERS), any(Map.class), any(Class.class)))
        .thenReturn(2L);

    when(mockJdbcTemplate.batchUpdate(
            eq(SuperPowerRepositoryImpl.INSERT_HERO_SUPER_POWER), any(SqlParameterSource[].class)))
        .thenReturn(new int[] {1, 1});

    var actual = superPowerRepository.updateHeroSuperPowers(heroId, superPowers);

    assertTrue(expected.containsAll(actual));
    verify(mockJdbcTemplate, times(1))
        .queryForObject(
            eq(SuperPowerRepositoryImpl.COUNT_SUPER_POWERS), any(Map.class), any(Class.class));

    verify(mockJdbcTemplate, times(1))
        .queryForObject(
            eq(SuperPowerRepositoryImpl.COUNT_SUPER_POWERS), any(Map.class), any(Class.class));
  }

  @Test
  public void updateHeroesSuperPowers_Invalid_Super_Powers_Test() {
    var heroId = 1L;
    var superPowers = List.of(HeroTestsUtils.buildDummySuperPower(1L));
    when(mockJdbcTemplate.queryForObject(
            eq(SuperPowerRepositoryImpl.COUNT_SUPER_POWERS), any(Map.class), any(Class.class)))
        .thenReturn(0L);

    assertThrows(
        HeroBadRequestException.class,
        () -> superPowerRepository.updateHeroSuperPowers(heroId, superPowers));
    verify(mockJdbcTemplate, times(1))
        .queryForObject(
            eq(SuperPowerRepositoryImpl.COUNT_SUPER_POWERS), any(Map.class), any(Class.class));
  }
}
