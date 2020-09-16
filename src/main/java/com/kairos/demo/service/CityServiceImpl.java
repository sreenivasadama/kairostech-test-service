/**
 * 
 */
package com.kairos.demo.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kairos.demo.City;
import com.kairos.demo.controller.CityController;

/**
 * @author sdama
 *
 */
@Component
public class CityServiceImpl implements CityService {
	private static final Logger logger = LoggerFactory.getLogger(CityController.class);
	private static final String YES = "Yes";
	private static final String NO = "No";
	
	
	private static Map<String, City> cityMap = new HashMap<>();

    private City getCity(String name) {
        return cityMap.get(name);
    }
	
    /**
     * Implementation method
     * @origin, @destination
     * return string
     */
	@Override
	public String citiesConnected(String origin, String destination) {
		
		if(cityMap.isEmpty()) {
			createRoadsMap();
		}
		
		City oCity = getCity(origin.toUpperCase());
        City dCity = getCity(destination.toUpperCase());

        
        return getConnection(oCity, dCity) ? YES : NO;
		
	}
	
	
	private void createRoadsMap() {
		
		try (Stream<String> stream = Files.lines( 
				Paths.get(getClass().getClassLoader()
			      .getResource("cities.txt").toURI()))) {
			
			stream.forEach(line -> {
				String[] connections = line.split(",");
				
	            String origin = connections[0].trim().toUpperCase();
	            String destiny = connections[1].trim().toUpperCase();

	            if (!origin.equals(destiny)) {
	                City oCity = cityMap.getOrDefault(origin, createCity(origin));
	                City dCity = cityMap.getOrDefault(destiny, createCity(destiny));

	                oCity.addNearby(dCity);
	                dCity.addNearby(oCity);

	                cityMap.put(oCity.getName(), oCity);
	                cityMap.put(dCity.getName(), dCity);
	            }
			});

		} catch (IOException e) {
		  logger.error(e.getMessage());
		} catch (URISyntaxException ue) {
			logger.error(ue.getMessage());
		}
		
	}
	
	private boolean getConnection(City origin, City destination) {
		
		if (origin.equals(destination)) 
			return true;

        if (origin.getNearby().contains(destination)) 
        	return true;
		
        Set<City> visited = new HashSet<>(Collections.singleton(origin));

        Deque<City> bucketlist = new ArrayDeque<>(origin.getNearby());
        
        while (!bucketlist.isEmpty()) {
            City city = bucketlist.getLast();
            if (city.equals(destination)) return true;
            if (!visited.contains(city)) {
                visited.add(city);
                bucketlist.addAll(city.getNearby());
                bucketlist.removeAll(visited);
            } else {
                bucketlist.removeAll(Collections.singleton(city));
            }
        }

        return false;
	}
	
	/**
     * 
     * @param name
     * @return City Object
     */
    public City createCity(String name) {
        return new City(name);
    }
}
