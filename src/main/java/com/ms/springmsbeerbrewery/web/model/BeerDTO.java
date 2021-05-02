package com.ms.springmsbeerbrewery.web.model;

import java.util.UUID;

import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO
{
  @Null
  private UUID id;

  @NotBlank
  private String beer;

  @NotBlank
  private String beerStyle;

  @Positive
  private long upc;
}
