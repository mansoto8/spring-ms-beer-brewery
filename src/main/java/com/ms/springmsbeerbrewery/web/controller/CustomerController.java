package com.ms.springmsbeerbrewery.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.ms.springmsbeerbrewery.services.BeerService;
import com.ms.springmsbeerbrewery.services.CustomerService;
import com.ms.springmsbeerbrewery.web.model.BeerDTO;
import com.ms.springmsbeerbrewery.web.model.CustomerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController
{
  private final CustomerService customerService;

  public CustomerController(final CustomerService customerService) {this.customerService = customerService;}

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerDTO> getBeer(@PathVariable("customerId") UUID customerId) {
    return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity handlePost(@Valid @RequestBody CustomerDTO customerDTO) {
    CustomerDTO savedDTO = customerService.saveCustomer(customerDTO);

    HttpHeaders httpHeaders = new HttpHeaders();
    //todo add host name
    httpHeaders.add("Location", "/api/v1/customer/" + savedDTO.getId().toString());

    return new ResponseEntity<>(savedDTO, httpHeaders, HttpStatus.CREATED);
  }

  @PutMapping({"/{customerId}"})
  public ResponseEntity handleUpdate(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerDTO){

    customerService.updateCustomer(customerId, customerDTO);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping({"/{customerId}"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable("customerId") UUID customerId){
    customerService.deleteById(customerId);
  }
}
