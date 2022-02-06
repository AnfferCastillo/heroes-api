package com.anffercastillo.heroes.dto;

import java.util.Objects;

public class HeroDTO {

  private long id;
  private String name;
  private String forename;

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