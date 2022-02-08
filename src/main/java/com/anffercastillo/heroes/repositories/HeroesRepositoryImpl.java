package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.entities.Hero;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HeroesRepositoryImpl implements HeroesRepositoryCustom {

  // PROTECTED FOR TESTING PURPUSES
  protected static final String QUERY_BY_NAME = "select * from heroes where name like ?";

  EntityManager entityManager;

  public HeroesRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Hero> findHeroesByName(String name) {
    Query query = entityManager.createNativeQuery(QUERY_BY_NAME, Hero.class);
    var parameter = new StringBuilder("%").append(name).append("%");
    query.setParameter(1, parameter.toString());
    return query.getResultList();
  }
}
