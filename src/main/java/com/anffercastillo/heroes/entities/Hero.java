package com.anffercastillo.heroes.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
  private List<SuperPowers> superPowers;

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

  public List<SuperPowers> getSuperPowers() {
    return superPowers;
  }

  public void setSuperPowers(List<SuperPowers> superPowers) {
    this.superPowers = superPowers;
  }
}
