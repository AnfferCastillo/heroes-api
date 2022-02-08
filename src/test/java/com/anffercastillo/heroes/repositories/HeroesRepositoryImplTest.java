package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.entities.Hero;
import com.anffercastillo.heroes.utils.HeroTestsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HeroesRepositoryImplTest {

  private HeroesRepositoryImpl heroesRepository;

  private EntityManager mockEntityManager;

  private Query mockQuery;

  @BeforeEach
  public void setup() {
    mockEntityManager = mock(EntityManager.class);
    mockQuery = mock(Query.class);
    heroesRepository = new HeroesRepositoryImpl(mockEntityManager);
  }

  @Test
  public void findHeroesByName_Test() {
    var name = "DUMMY";

    var dummyHero = HeroTestsUtils.buildDummyHeroEntity(1L);
    var dummyHero2 = HeroTestsUtils.buildDummyHeroEntity(2L);

    var expected = List.of(dummyHero, dummyHero2);

    when(mockEntityManager.createNativeQuery(HeroesRepositoryImpl.QUERY_BY_NAME, Hero.class))
        .thenReturn(mockQuery);
    when(mockQuery.getResultList()).thenReturn(List.of(dummyHero, dummyHero2));

    var actual = heroesRepository.findHeroesByName(name);

    verify(mockEntityManager, times(1))
        .createNativeQuery(HeroesRepositoryImpl.QUERY_BY_NAME, Hero.class);
    verify(mockQuery, times(1)).getResultList();

    assertEquals(expected.size(), actual.size());
    assertEquals(expected.get(0), dummyHero);
    assertEquals(expected.get(1), dummyHero2);
  }
}
