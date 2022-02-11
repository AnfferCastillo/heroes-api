package com.anffercastillo.heroes.repositories;

import java.util.List;

import com.anffercastillo.heroes.dto.SuperPowerDTO;

public interface SuperPowerRepository {

  List<SuperPowerDTO> findSuperPowerByHeroId(long id);
}
