package com.ms.springmsbeerbrewery.web.mappers;

import com.ms.springmsbeerbrewery.domain.Customer;
import com.ms.springmsbeerbrewery.web.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper
{
  CustomerDTO customerToCustomerDTO(Customer Customer);

  Customer customerDTOToCustomer(CustomerDTO CustomerDTO);
}
