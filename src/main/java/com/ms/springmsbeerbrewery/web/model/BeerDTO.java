package com.ms.springmsbeerbrewery.web.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
  @Size(min = 3, max = 10)
  private String beerStyle;

  @Positive
  private long upc;

  private OffsetDateTime created;
  private OffsetDateTime lastUpdatedDate;
}
