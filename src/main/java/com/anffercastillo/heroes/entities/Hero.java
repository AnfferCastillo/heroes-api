package com.anffercastillo.heroes.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HEROES")
public class Hero {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column private String name;

  @Column private String forename;

  @Column
  @Enumerated(EnumType.STRING)
  private HeroesCompany company;

  @ManyToMany
  @JoinTable(
      name = "heroes_super_powers",
      joinColumns = @JoinColumn(name = "hero_id"),
      inverseJoinColumns = @JoinColumn(name = "super_power_id"))
  private List<SuperPowers> list;

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
