package com.ms.springmsbeerbrewery.services.impl;

import java.util.UUID;

import com.ms.springmsbeerbrewery.services.CustomerService;
import com.ms.springmsbeerbrewery.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl
    implements CustomerService
{
  @Override
  public CustomerDTO getCustomerById(final UUID beerId) {
    return CustomerDTO.builder()
        .id(beerId)
        .name("Nicko")
        .build();
  }

  @Override
  public CustomerDTO saveCustomer(final CustomerDTO customerDTO) {
    return customerDTO.builder()
        .id(UUID.randomUUID())
        .build();
  }

  @Override
  public void updateCustomer(UUID customerId, CustomerDTO customerDTO) {
    //todo impl - would add a real impl to update beer
  }

  @Override
  public void deleteById(UUID customerId) {
    log.debug("Deleting a beer...");
  }
}
