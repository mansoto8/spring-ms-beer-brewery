package com.ms.springmsbeerbrewery.web.controller;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.springmsbeerbrewery.services.impl.BeerServiceImpl;
import com.ms.springmsbeerbrewery.web.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev.springframework.ms", uriPort = 80)
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
    mockMvc.perform(
        get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
            .param("iscold", "yes")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(document("v1/beer-get",
            pathParameters(
                parameterWithName("beerId").description("UUID of desired  beer to get")
            ),
            requestParameters(
                parameterWithName("iscold").description("Is beer Cold query param")
            ),
            responseFields(
                fieldWithPath("id").description("Id of Beer"),
                fieldWithPath("created").description("Date Created"),
                fieldWithPath("lastUpdatedDate").description("Date Updated"),
                fieldWithPath("beer").description("Beer Name"),
                fieldWithPath("beerStyle").description("Beer Style"),
                fieldWithPath("upc").description("UPC of Beer")
            )
            ));
  }

  @Test
  void saveNewBeer() throws Exception {
    BeerDTO beerDTO = BeerDTO.builder().beer("Poker").beerStyle("Rubia").upc(134123L).build();
    String beerDTOJson = objectMapper.writeValueAsString(beerDTO);

    ConstrainedFields fields = new ConstrainedFields(BeerDTO.class);

    mockMvc.perform(
        post("/api/v1/beer/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDTOJson)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(document("v1/beer-new",
            requestFields(
                fields.withPath("id").ignored(),
                fields.withPath("created").ignored(),
                fields.withPath("lastUpdatedDate").ignored(),
                fields.withPath("beer").description("Beer Name"),
                fields.withPath("beerStyle").description("Beer Style"),
                fields.withPath("upc").description("UPC of Beer").attributes()
            )));
  }

  @Test
  void updateBeerById() throws Exception {
    BeerDTO beerDTO = BeerDTO.builder().build();
    String beerDTOJson = objectMapper.writeValueAsString(beerDTO);

    mockMvc.perform(
        put("/api/v1/beer/5424974f-46ab-4cbd-88a6-2597fc52fb96 \n")
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDTOJson)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  private static class ConstrainedFields {

    private final ConstraintDescriptions constraintDescriptions;

    ConstrainedFields(Class<?> input) {
      this.constraintDescriptions = new ConstraintDescriptions(input);
    }

    private FieldDescriptor withPath(String path) {
      return fieldWithPath(path).attributes(key("constraints").value(StringUtils
          .collectionToDelimitedString(this.constraintDescriptions
              .descriptionsForProperty(path), ". ")));
    }
  }
}