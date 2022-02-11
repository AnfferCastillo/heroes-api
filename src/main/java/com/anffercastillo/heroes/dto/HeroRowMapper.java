package com.anffercastillo.heroes.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HeroRowMapper implements RowMapper<HeroDTO> {

  @Override
  public HeroDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    var dto = new HeroDTO();
    dto.setCompany(rs.getString("company"));
    dto.setForename(rs.getString("forename"));
    dto.setName(rs.getString("name"));
    dto.setId(rs.getLong("id"));
    return dto;
  }
}
