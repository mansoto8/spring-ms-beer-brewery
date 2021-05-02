package com.ms.springmsbeerbrewery.web.controller;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.springmsbeerbrewery.services.impl.BeerServiceImpl;
import com.ms.springmsbeerbrewery.web.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BeerController.class)
@ContextConfiguration(classes = {BeerController.class, BeerServiceImpl.class})
class BeerControllerTest
{
  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void getBeerById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/beer/" + UUID.randomUUID().toString())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void saveNewBeer() throws Exception {
    BeerDTO beerDTO = BeerDTO.builder().beer("Poker").beerStyle("Rubia").upc(134123L).build();
    String beerDTOJson = objectMapper.writeValueAsString(beerDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/api/v1/beer/")
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
        .put("/api/v1/beer/5424974f-46ab-4cbd-88a6-2597fc52fb96 \n")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDTOJson)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }
}