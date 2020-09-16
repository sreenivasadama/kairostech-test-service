/**
 * 
 */
package com.kairos.demo.controller;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kairos.demo.City;
import com.kairos.demo.service.CityService;

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
	public String connected(@RequestParam String origin, @RequestParam String destination) {
		System.out.println("In Controller");
		
		return cityService.citiesConnected(origin, destination);
	}
	
}
