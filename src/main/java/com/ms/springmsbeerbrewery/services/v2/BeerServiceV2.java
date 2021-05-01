package com.ms.springmsbeerbrewery.services.v2;

import java.util.UUID;

import com.ms.springmsbeerbrewery.web.model.v2.BeerDTOV2;

public interface BeerServiceV2
{
  BeerDTOV2 getBeerById(UUID beerId);

  BeerDTOV2 saveBeer(BeerDTOV2 beerDTO);

  void updateBeer(UUID beerId, BeerDTOV2 beerDTO);

  void deleteById(UUID beerId);
}
