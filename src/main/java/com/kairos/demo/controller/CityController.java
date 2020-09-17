/**
 * Rest controller
 */
package com.kairos.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.kairos.demo.service.CityService;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author sdama
 *
 */
@RestController
public class CityController {

	private static final Logger logger = LoggerFactory.getLogger(CityController.class);
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/connected")
	public ResponseEntity<String> connected(@RequestParam String origin, @RequestParam String destination) {
		if(StringUtils.isBlank(origin)) {
			logger.debug("Origin city is null");
			return ResponseEntity.badRequest().body("Origin city missing!");
		}
        if(StringUtils.isBlank(origin)) {
        	logger.debug("Destination city is null");
        	return ResponseEntity.badRequest().body("Destination city missing!");
        }
        
		return ResponseEntity.ok().body(cityService.citiesConnected(origin, destination));
	}
	
}
