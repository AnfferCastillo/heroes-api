package com.anffercastillo.heroes.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRowMapper;
import com.anffercastillo.heroes.dto.SuperPowerDTO;

@Repository
public class HeroesRepositoryImpl implements HeroesRepository {

  // PROTECTED FOR TESTING PURPUSES
  protected static final String QUERY_BY_NAME =
      "select * from heroes where lower(name) like lower('%:name%')";

  protected static final String QUERY_ALL = "select * from heroes";

  private NamedParameterJdbcTemplate jdbcTemplate;
  private SuperPowerRepository superPowersRepository;

  public HeroesRepositoryImpl(
      NamedParameterJdbcTemplate jdbcTemplate, SuperPowerRepository superPowersRepository) {
    this.jdbcTemplate = jdbcTemplate;
    this.superPowersRepository = superPowersRepository;
  }

  @Override
  public List<HeroDTO> findHeroesByName(String name) {
    var parametersMap = new HashMap<String, String>();
    parametersMap.put("name", name);
    return jdbcTemplate
        .query(QUERY_BY_NAME, parametersMap, new HeroRowMapper())
        .stream()
        .map(this::setHeroSuperPowers)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<HeroDTO> findHeroById(long id) {
    return null;
  }

  @Override
  public List<HeroDTO> findAll() {
    return jdbcTemplate
        .query(QUERY_ALL, new HeroRowMapper())
        .stream()
        .map(this::setHeroSuperPowers)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(long id) {
    // TODO Auto-generated method stub

  }

  @Override
  public HeroDTO save(HeroDTO hero) {
    // TODO Auto-generated method stub
    return null;
  }

  private HeroDTO setHeroSuperPowers(HeroDTO dto) {
    var superPowers =
        superPowersRepository
            .findSuperPowerByHeroId(dto.getId())
            .stream()
            .map(SuperPowerDTO::getDescription)
            .collect(Collectors.toList());
    dto.setSuperPowers(superPowers);
    return dto;
  }
}
