package com.anffercastillo.heroes.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperPowerRowMapper implements RowMapper<SuperPowerDTO> {

  @Override
  public SuperPowerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    var dto = new SuperPowerDTO();
    dto.setName(rs.getString("name"));
    dto.setId(rs.getLong("id"));
    return dto;
  }
}
