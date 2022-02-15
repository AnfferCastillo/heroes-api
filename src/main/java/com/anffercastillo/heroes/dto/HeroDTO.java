package com.anffercastillo.heroes.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HeroDTO {

  private long id;
  private String name;
  private String forename;
  private List<SuperPowerDTO> superPowers;
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

  public List<SuperPowerDTO> getSuperPowers() {
    return superPowers;
  }

  public void setSuperPowers(List<SuperPowerDTO> superPowers) {
    this.superPowers = superPowers;
  }

  public static HeroDTO buildHeroDTO(long id, HeroRequest request) {
    var dto = new HeroDTO();
    dto.setId(id);
    dto.setName(request.getName());
    dto.setForename(request.getForename());
    dto.setCompany(request.getCompany().name());
    dto.setSuperPowers(new ArrayList<>());
    dto.getSuperPowers().addAll(request.getSuperPowers());

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
