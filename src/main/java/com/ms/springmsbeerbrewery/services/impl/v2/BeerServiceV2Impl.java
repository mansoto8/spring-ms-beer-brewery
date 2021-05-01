package com.ms.springmsbeerbrewery.services.impl.v2;

import java.util.UUID;

import com.ms.springmsbeerbrewery.services.v2.BeerServiceV2;
import com.ms.springmsbeerbrewery.web.model.v2.BeerDTOV2;
import com.ms.springmsbeerbrewery.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BeerServiceV2Impl
    implements BeerServiceV2
{
  @Override
  public BeerDTOV2 getBeerById(final UUID beerId) {
    return BeerDTOV2.builder()
        .id(beerId)
        .beer("Costeña")
        .beerStyle(BeerStyleEnum.ALE)
        .build();
  }

  @Override
  public BeerDTOV2 saveBeer(final BeerDTOV2 beerDTO) {
    return BeerDTOV2.builder()
        .id(UUID.randomUUID())
        .build();
  }

  @Override
  public void updateBeer(UUID beerId, BeerDTOV2 beerDTO) {
    //todo impl - would add a real impl to update beer
  }

  @Override
  public void deleteById(UUID beerId) {
    log.debug("Deleting a beer...");
  }
}
