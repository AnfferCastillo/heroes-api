package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.dto.SuperPowerDTO;
import com.anffercastillo.heroes.dto.SuperPowerRowMapper;
import com.anffercastillo.heroes.exceptions.HeroBadRequestException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SuperPowerRepositoryImpl implements SuperPowerRepository {

  protected static final String QUERY_BY_HERO_ID =
      "select sp.* from heroes_super_powers hsp join super_powers sp on hsp.super_power_id = sp.id and hsp.hero_id = :hero_id";

  protected static final String QUERY_BY_ID = "select * from super_powers where id = :id";
  protected static final String DELETE_HERO_SUPER_POWERS =
      "delete from heroes_super_powers where hero_id = :id";

  protected static final String COUNT_SUPER_POWERS =
      "select count(1) from super_powers where name in (:names)";

  protected static final String INSERT_HERO_SUPER_POWER =
      "insert into heroes_super_powers (hero_id, super_power_id) values (:hero_id, :super_power_id)";

  private NamedParameterJdbcTemplate jdbcTemplate;

  public SuperPowerRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<SuperPowerDTO> findSuperPowerByHeroId(long id) {
    return jdbcTemplate.query(QUERY_BY_HERO_ID, Map.of("hero_id", id), new SuperPowerRowMapper());
  }

  @Override
  public List<SuperPowerDTO> updateHeroSuperPowers(long heroId, List<SuperPowerDTO> superPowers) {
    if (!validateSuperPowerList(superPowers)) {
      throw new HeroBadRequestException();
    }

    deleteHeroSuperPowers(heroId);

    jdbcTemplate.batchUpdate(
        INSERT_HERO_SUPER_POWER, getParamtersMapForUpdate(heroId, superPowers));
    return superPowers;
  }

  @Override
  public Optional<SuperPowerDTO> findById(long id) {
    var superPower =
        jdbcTemplate.queryForObject(QUERY_BY_ID, Map.of("id", id), new SuperPowerRowMapper());
    return Optional.ofNullable(superPower);
  }

  private void deleteHeroSuperPowers(long heroId) {
    jdbcTemplate.update(DELETE_HERO_SUPER_POWERS, Map.of("hero_id", heroId));
  }

  private boolean validateSuperPowerList(List<SuperPowerDTO> superPowers) {
    var superPowerNames =
        superPowers.stream().map(SuperPowerDTO::getName).collect(Collectors.toList());
    var count =
        jdbcTemplate.queryForObject(
            COUNT_SUPER_POWERS, Map.of("names", superPowerNames), Long.class);

    // this count should match, otherwise one of the super powers does not exist.
    return count == superPowers.size();
  }

  private SqlParameterSource[] getParamtersMapForUpdate(
      long heroId, List<SuperPowerDTO> superPowers) {
    var paramsMaps =
        superPowers.stream()
            .map(
                sp ->
                    new MapSqlParameterSource(
                        Map.of("hero_id", heroId, "super_power_id", sp.getId())))
            .collect(Collectors.toList());
    return paramsMaps.toArray(SqlParameterSource[]::new);
  }
}
