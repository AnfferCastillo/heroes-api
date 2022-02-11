package com.anffercastillo.heroes.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.anffercastillo.heroes.dto.HeroRowMapper;
import com.anffercastillo.heroes.utils.HeroTestsUtils;

public class HeroesRepositoryImplTest {

  private HeroesRepositoryImpl heroesRepository;

  private NamedParameterJdbcTemplate mockJdbcTemplate;

  private SuperPowerRepository mockSuperPowersRepository;

  @BeforeEach
  public void setup() {
    mockJdbcTemplate = mock(NamedParameterJdbcTemplate.class);
    mockSuperPowersRepository = mock(SuperPowerRepository.class);

    heroesRepository = new HeroesRepositoryImpl(mockJdbcTemplate, mockSuperPowersRepository);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void findHeroesByName_Test() {
    var name = "DUMMY";

    var dummyHero = HeroTestsUtils.buildDummyHero(1L);
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
}
