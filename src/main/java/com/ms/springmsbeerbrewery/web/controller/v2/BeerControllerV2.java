package com.ms.springmsbeerbrewery.web.controller.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ms.springmsbeerbrewery.services.v2.BeerServiceV2;
import com.ms.springmsbeerbrewery.web.model.v2.BeerDTOV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@Validated
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2
{
  private final BeerServiceV2 beerService;

  public BeerControllerV2(final BeerServiceV2 beerService) {this.beerService = beerService;}

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDTOV2> getBeer(@NotNull @PathVariable("beerId") UUID beerId) {
    return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity handlePost(@Valid @RequestBody BeerDTOV2 beerDTO) {
    BeerDTOV2 savedDTO = beerService.saveBeer(beerDTO);

    HttpHeaders httpHeaders = new HttpHeaders();
    //todo add host name
    httpHeaders.add("Location", "/api/v1/beer/" + savedDTO.getId().toString());

    return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
  }

  @PutMapping({"/{beerId}"})
  public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDTOV2 beerDto){

    beerService.updateBeer(beerId, beerDto);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping({"/{beerId}"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable("beerId") UUID beerId){
    beerService.deleteById(beerId);
  }
}
