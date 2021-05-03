package com.ms.springmsbeerbrewery.domain;

import java.sql.Timestamp;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer
{
  private UUID id;
  private String beer;
  private String beerStyle;
  private long upc;

  private Timestamp createdDate;
  private Timestamp lastUpdateDate;
}
