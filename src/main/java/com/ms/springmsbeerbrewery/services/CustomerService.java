package com.ms.springmsbeerbrewery.services;

import java.util.UUID;

import com.ms.springmsbeerbrewery.web.model.CustomerDTO;

public interface CustomerService
{
  CustomerDTO getCustomerById(UUID beerId);

  CustomerDTO saveCustomer(final CustomerDTO customerDTO);

  void updateCustomer(UUID customerId, CustomerDTO customerDTO);

  void deleteById(UUID customerId);
}
