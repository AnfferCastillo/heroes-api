package com.anffercastillo.heroes.dto;

import com.anffercastillo.heroes.entities.HeroesCompany;

import java.util.List;

public class HeroRequest {

  private String name;

  private String forename;

  private HeroesCompany company;

  private List<String> superPowers;

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

  public HeroesCompany getCompany() {
    return company;
  }

  public void setCompany(HeroesCompany company) {
    this.company = company;
  }

  public List<String> getSuperPowers() {
    return superPowers;
  }

  public void setSuperPowers(List<String> superPowers) {
    this.superPowers = superPowers;
  }
}
