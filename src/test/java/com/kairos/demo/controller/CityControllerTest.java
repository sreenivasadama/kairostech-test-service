package com.kairos.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.kairos.demo.service.CityService;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value=CityController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CityControllerTest {
   @Autowired
   private MockMvc mvc;
   
   @MockBean
   private CityController cityController;
   
   @MockBean
   private CityService cityService;
   
   @Test
   public void connectedShouldReturn_BadRequest_withInvalidRequest() throws Exception {
	  
       mvc.perform(get("/connected?origin=city1")
    		   .contentType(APPLICATION_JSON))
               .andExpect(status().isBadRequest());
   }
   
   @Test
   public void connectedShouldReturn_OK_withValidRequest() throws Exception {
      
       given(cityService.citiesConnected("city1", "city2")).willReturn("yes");
       mvc.perform(get("/connected?origin=city1&destination=city2")
    		   .contentType(APPLICATION_JSON))
               .andExpect(status().isOk());
   }
   
}