package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.dto.HeroDTO;
import com.anffercastillo.heroes.dto.HeroRowMapper;
import com.anffercastillo.heroes.exceptions.HeroNotFoundException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class HeroesRepositoryImpl implements HeroesRepository {

  protected static final String SAVE_HERO =
      "update heroes set name = :name, forename = :forename, company = :company where id = :id";
  // PROTECTED FOR TESTING PURPUSES
  protected static final String QUERY_BY_NAME = "select * from heroes where lower(name) like :name";

  protected static final String QUERY_ALL = "select * from heroes";

  protected static final String QUERY_BY_ID = "select * from heroes where id = :id";

  protected static final String DELETE_BY_ID = "delete from heroes where id = :id";

  private NamedParameterJdbcTemplate jdbcTemplate;
  private SuperPowerRepository superPowersRepository;

  public HeroesRepositoryImpl(
      NamedParameterJdbcTemplate jdbcTemplate, SuperPowerRepository superPowersRepository) {
    this.jdbcTemplate = jdbcTemplate;
    this.superPowersRepository = superPowersRepository;
  }

  @Override
  public List<HeroDTO> findHeroesByName(String name) {
    var param = new StringBuilder().append("%").append(name.toLowerCase()).append("%");
    return jdbcTemplate
        .query(QUERY_BY_NAME, Map.of("name", param.toString()), new HeroRowMapper())
        .stream()
        .map(this::setHeroSuperPowers)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<HeroDTO> findById(long id) {
    var hero = jdbcTemplate.queryForObject(QUERY_BY_ID, Map.of("id", id), new HeroRowMapper());

    if (hero != null) {
      setHeroSuperPowers(hero);
    }
    return Optional.ofNullable(hero);
  }

  @Override
  public List<HeroDTO> findAll() {
    return jdbcTemplate.query(QUERY_ALL, new HeroRowMapper()).stream()
        .map(this::setHeroSuperPowers)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(long id) {
    superPowersRepository.deleteHeroSuperPowers(id);
    var count = jdbcTemplate.update(DELETE_BY_ID, Map.of("id", id));

    if (count == 0) {
      throw new HeroNotFoundException();
    }
  }

  @Override
  public HeroDTO save(HeroDTO hero) {

    superPowersRepository.updateHeroSuperPowers(hero.getId(), hero.getSuperPowers());

    var count =
        jdbcTemplate.update(
            SAVE_HERO,
            Map.of(
                "name",
                hero.getName(),
                "forename",
                hero.getForename(),
                "company",
                hero.getCompany(),
                "id",
                hero.getId()));

    // one row was updated
    if (count == 1) {
      return hero;
    } else {
      throw new HeroNotFoundException();
    }
  }

  private HeroDTO setHeroSuperPowers(HeroDTO dto) {
    var superPowers = superPowersRepository.findSuperPowerByHeroId(dto.getId());
    dto.setSuperPowers(superPowers);
    return dto;
  }
}
