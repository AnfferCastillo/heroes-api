package com.anffercastillo.heroes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anffercastillo.heroes.dto.HeroeDTO;

@Service
public class HeroesService {

  // TODO: move this to a repository.
  List<HeroeDTO> heroes;

  public HeroesService() {
    var heroe1 = new HeroeDTO();
    heroe1.setId(1L);
    heroe1.setName("Spiderman");
    heroe1.setForename("Peter Parker");

    var heroe2 = new HeroeDTO();
    heroe2.setId(2L);
    heroe2.setName("Batman");
    heroe2.setForename("Bruce Wayne");

    var heroe3 = new HeroeDTO();
    heroe3.setId(3L);
    heroe3.setName("Vegeta");
    heroe3.setForename("Vegeta");
    heroes = List.of(heroe1, heroe2, heroe3);
  }

  public HeroeDTO getHeroe(long id) {
    var heroe = this.heroes.stream().filter(h -> h.getId() == id).findFirst();
    // TODO: change this to a proper exception with 404
    return heroe.orElseThrow();
  }
}
