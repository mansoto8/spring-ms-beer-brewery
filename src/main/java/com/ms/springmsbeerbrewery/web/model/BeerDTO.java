package com.ms.springmsbeerbrewery.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO
{
  private UUID id;
  private String beer;
  private String beerStyle;
  private long upc;
}
