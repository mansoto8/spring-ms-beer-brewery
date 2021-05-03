package com.ms.springmsbeerbrewery.web.mappers;

import com.ms.springmsbeerbrewery.domain.Beer;
import com.ms.springmsbeerbrewery.web.model.BeerDTO;
import org.mapstruct.Mapper;

//This should be used if mapstruct were working
//@Mapper(uses = DateMapper.class)
@Mapper
public interface BeerMapper
{
  BeerDTO beerToBeerDTO(Beer beer);

  Beer beerDTOToBeer(BeerDTO beerDTO);
}
