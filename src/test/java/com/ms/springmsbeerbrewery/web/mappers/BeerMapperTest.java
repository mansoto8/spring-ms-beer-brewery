package com.ms.springmsbeerbrewery.web.mappers;

import com.ms.springmsbeerbrewery.domain.Beer;
import com.ms.springmsbeerbrewery.web.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = {BeerMapperImpl.class})
class BeerMapperTest
{
  @Autowired
  BeerMapper beerMapper;

  @Test
  void beerToBeerDTO() {
    BeerDTO beerDTO = BeerDTO.builder().beer("Poker").beerStyle("Rubia").upc(134123L).build();
    Beer beer = beerMapper.beerDTOToBeer(beerDTO);
    Assert.notNull(beer, "beer must not be null");
  }

  @Test
  void beerDTOToBeer() {
    Beer beer = Beer.builder().beer("Poker").beerStyle("Rubia").upc(134123L).build();
    BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
    Assert.notNull(beerDTO, "beer must not be null");
  }
}