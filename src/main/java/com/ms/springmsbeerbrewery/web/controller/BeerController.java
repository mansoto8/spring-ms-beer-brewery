package com.ms.springmsbeerbrewery.web.controller;

import java.util.UUID;

import com.ms.springmsbeerbrewery.services.BeerService;
import com.ms.springmsbeerbrewery.web.model.BeerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController
{
  private final BeerService beerService;

  public BeerController(final BeerService beerService) {this.beerService = beerService;}

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDTO> getBeer(@PathVariable("beerId") UUID beerId) {
    return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity handlePost(@RequestBody BeerDTO beerDTO) {
    BeerDTO savedDTO = beerService.saveBeer(beerDTO);

    HttpHeaders httpHeaders = new HttpHeaders();
    //todo add host name
    httpHeaders.add("Location", "/api/v1/beer/" + savedDTO.getId().toString());

    return new ResponseEntity<>(savedDTO, httpHeaders, HttpStatus.CREATED);
  }

  @PutMapping({"/{beerId}"})
  public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDto){

    beerService.updateBeer(beerId, beerDto);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping({"/{beerId}"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable("beerId") UUID beerId){
    beerService.deleteById(beerId);
  }
}
