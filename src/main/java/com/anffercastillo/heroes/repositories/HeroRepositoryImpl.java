package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.entities.Heroes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HeroRepositoryImpl implements HeroesRepositoryCustom {

  @PersistenceContext EntityManager entityManager;

  @Override
  public List<Heroes> findHeroesByName(String string) { // TODO Auto-generated method stub
    return null;
  }
}
