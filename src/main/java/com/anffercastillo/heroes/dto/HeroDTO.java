package com.anffercastillo.heroes.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.anffercastillo.heroes.entities.Hero;
import com.anffercastillo.heroes.entities.SuperPowers;

public class HeroDTO {

  private long id;
  private String name;
  private String forename;
  private List<String> superPowers;
  private String company;

  public String getCompany() {
    return company;
  }

  public void setCompany(String compay) {
    this.company = compay;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getForename() {
    return forename;
  }

  public void setForename(String forename) {
    this.forename = forename;
  }

  public List<String> getSuperPowers() {
    return superPowers;
  }

  public void setSuperPowers(List<String> superPowers) {
    this.superPowers = superPowers;
  }

  public static HeroDTO buildHeroDTO(Hero entity) {
    var dto = new HeroDTO();
    dto.setName(entity.getName());
    dto.setForename(entity.getForename());
    dto.setId(entity.getId());
    dto.setCompany(entity.getCompany().name());
    dto.setSuperPowers(
        entity.getSuperPowers().stream().map(SuperPowers::getName).collect(Collectors.toList()));
    return dto;
  }

  @Override
  public int hashCode() {
    return Objects.hash(forename, id, name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    HeroDTO other = (HeroDTO) obj;
    return Objects.equals(forename, other.forename)
        && id == other.id
        && Objects.equals(name, other.name);
  }
}
