package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.entities.Heroes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroesRepository extends JpaRepository<Heroes, Long>, HeroesRepositoryCustom {

  Optional<Heroes> findHeroesById(long id);

  HeroDTO updateHero(HeroDTO hero);
}
