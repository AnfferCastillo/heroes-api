package com.anffercastillo.heroes.repositories;

import com.anffercastillo.heroes.dto.SuperPowerDTO;

import java.util.List;
import java.util.Optional;

public interface SuperPowerRepository {

  List<SuperPowerDTO> findSuperPowerByHeroId(long id);

  List<SuperPowerDTO> updateHeroSuperPowers(long heroId, List<SuperPowerDTO> superPowes);

  Optional<SuperPowerDTO> findById(long id);
}
