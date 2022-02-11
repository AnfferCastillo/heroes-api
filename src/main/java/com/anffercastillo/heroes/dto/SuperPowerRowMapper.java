package com.anffercastillo.heroes.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SuperPowerRowMapper implements RowMapper<SuperPowerDTO> {

  @Override
  public SuperPowerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    var dto = new SuperPowerDTO();
    dto.setDescription(rs.getString("name"));
    dto.setId(rs.getLong("id"));
    return dto;
  }
}
