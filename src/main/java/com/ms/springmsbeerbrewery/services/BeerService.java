package com.ms.springmsbeerbrewery.services;

import java.util.UUID;

import com.ms.springmsbeerbrewery.web.model.BeerDTO;

public interface BeerService
{
  BeerDTO getBeerById(UUID beerId);

  BeerDTO saveBeer(BeerDTO beerDTO);

  void updateBeer(UUID beerId, BeerDTO beerDTO);

  void deleteById(UUID beerId);
}
