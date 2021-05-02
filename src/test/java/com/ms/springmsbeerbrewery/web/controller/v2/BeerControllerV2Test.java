package com.ms.springmsbeerbrewery.web.controller.v2;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.springmsbeerbrewery.services.impl.BeerServiceImpl;
import com.ms.springmsbeerbrewery.services.impl.v2.BeerServiceV2Impl;
import com.ms.springmsbeerbrewery.web.controller.v2.BeerControllerV2;
import com.ms.springmsbeerbrewery.web.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.annotation.Validated;

@WebMvcTest(BeerControllerV2.class)
@ContextConfiguration(classes = {BeerControllerV2.class, BeerServiceV2Impl.class})
class BeerControllerV2Test
{
  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void getBeerById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v2/beer/" + UUID.randomUUID().toString())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void saveNewBeer() throws Exception {
    BeerDTO beerDTO = BeerDTO.builder().beer("Poker").beerStyle("PISLNER").upc(134123L).build();
    String beerDTOJson = objectMapper.writeValueAsString(beerDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/api/v2/beer/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDTOJson)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void updateBeerById() throws Exception {
    BeerDTO beerDTO = BeerDTO.builder().build();
    String beerDTOJson = objectMapper.writeValueAsString(beerDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .put("/api/v2/beer/5424974f-46ab-4cbd-88a6-2597fc52fb96")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDTOJson)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }
}