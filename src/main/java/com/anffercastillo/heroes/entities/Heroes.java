package com.anffercastillo.heroes.entities;

import javax.persistence.*;

@Entity
public class Heroes {

  @Id private long id;

  @Column private String name;

  @Column private String forename;

  @Column
  @Enumerated(EnumType.STRING)
  private HeroesCompany company;

  public HeroesCompany getCompany() {
    return company;
  }

  public void setCompany(HeroesCompany company) {
    this.company = company;
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
}
