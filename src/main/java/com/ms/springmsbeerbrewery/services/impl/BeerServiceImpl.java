package com.ms.springmsbeerbrewery.services.impl;

import java.util.Locale.Builder;
import java.util.UUID;

import com.ms.springmsbeerbrewery.services.BeerService;
import com.ms.springmsbeerbrewery.web.model.BeerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BeerServiceImpl
    implements BeerService
{
  @Override
  public BeerDTO getBeerById(final UUID beerId) {
    return BeerDTO.builder()
        .id(beerId)
        .beer("Costeña")
        .beerStyle("Pale Ale")
        .build();
  }

  @Override
  public BeerDTO saveBeer(final BeerDTO beerDTO) {
    return BeerDTO.builder()
        .id(UUID.randomUUID())
        .build();
  }

  @Override
  public void updateBeer(UUID beerId, BeerDTO beerDTO) {
    //todo impl - would add a real impl to update beer
  }

  @Override
  public void deleteById(UUID beerId) {
    log.debug("Deleting a beer...");
  }
}
