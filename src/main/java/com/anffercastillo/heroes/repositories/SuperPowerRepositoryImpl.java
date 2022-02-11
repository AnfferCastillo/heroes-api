package com.anffercastillo.heroes.repositories;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.anffercastillo.heroes.dto.SuperPowerDTO;
import com.anffercastillo.heroes.dto.SuperPowerRowMapper;

@Repository
public class SuperPowerRepositoryImpl implements SuperPowerRepository {

  protected static final String QUERY_BY_HERO_ID =
      "select sp.* from heroes_super_powers hsp join super_powers sp on hsp.super_power_id = sp.id and hsp.hero_id = :hero_id";

  private NamedParameterJdbcTemplate jdbcTemplate;

  public SuperPowerRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<SuperPowerDTO> findSuperPowerByHeroId(long id) {
    var parameters = new HashMap<String, Long>();
    parameters.put("hero_id", id);
    return jdbcTemplate.query(QUERY_BY_HERO_ID, parameters, new SuperPowerRowMapper());
  }
}
