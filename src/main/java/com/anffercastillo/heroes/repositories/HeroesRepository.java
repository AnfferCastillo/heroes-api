package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroesRepository extends JpaRepository<Hero, Long>, HeroesRepositoryCustom {

  Optional<Hero> findHeroesById(long id);
}
