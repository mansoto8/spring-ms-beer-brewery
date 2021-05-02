package com.ms.springmscustomerbrewery.web.controller;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.springmsbeerbrewery.services.impl.CustomerServiceImpl;
import com.ms.springmsbeerbrewery.web.controller.CustomerController;
import com.ms.springmsbeerbrewery.web.model.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CustomerController.class)
@ContextConfiguration(classes = {CustomerController.class, CustomerServiceImpl.class})
class CustomerControllerTest
{
  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void saveNewCustomer() throws Exception {
    CustomerDTO customerDTO = CustomerDTO.builder().name("aa").build();
    String customerDTOJson = objectMapper.writeValueAsString(customerDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/api/v1/customer/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(customerDTOJson)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }
}