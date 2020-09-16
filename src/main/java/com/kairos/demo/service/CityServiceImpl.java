/**
 * 
 */
package com.kairos.demo.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.kairos.demo.City;
import com.kairos.demo.ConnectedRoad;
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
	
	
	private Map<String, City> cityMap = new HashMap<>();

    private Map<String, City> getCityMap() {
        return cityMap;
    }

    private City getCity(String name) {
        return cityMap.get(name);
    }
	
	@Override
	public String citiesConnected(String origin, String destination) {
		if(cityMap.isEmpty()) {
			createRoadsMap();
		}
		City originCity = getCity(origin.toUpperCase());
        City destCity = getCity(destination.toUpperCase());

        Objects.requireNonNull(originCity);
        Objects.requireNonNull(destCity);

        return getConnection(originCity, destCity) ? YES : NO;
		
	}
	
	
	private void createRoadsMap() {
		System.out.println("In createdRoads Methos");
		
			try (Stream<String> stream = Files.lines( Paths.get(getClass().getClassLoader()
				      .getResource("cities.txt").toURI()))) {
				System.out.println("In streams 1...");
				stream.forEach(line -> {
					System.out.println("line::"+line);
					String[] connections = line.split(",");
					
		            String Akey = connections[0].trim().toUpperCase();
		            String Bkey = connections[1].trim().toUpperCase();

		            if (!Akey.equals(Bkey)) {
		                City A = cityMap.getOrDefault(Akey, City.build(Akey));
		                City B = cityMap.getOrDefault(Bkey, City.build(Bkey));

		                A.addNearby(B);
		                B.addNearby(A);

		                cityMap.put(A.getName(), A);
		                cityMap.put(B.getName(), B);
		            }
				});

			} catch (IOException e) {
			  logger.error(e.getMessage());
			} catch (URISyntaxException ue) {
				logger.error(ue.getMessage());
			}
			
		}
	
	private boolean getConnection(City origin, City destination) {
		if (origin.equals(destination)) return true;

        if (origin.getNearby().contains(destination)) return true;
		/*
         * The origin city was already visited since we have started from it
         */
        Set<City> visited = new HashSet<>(Collections.singleton(origin));

        /*
         * Put all the neighboring cities into a bucket list
         */
        Deque<City> bucketlist = new ArrayDeque<>(origin.getNearby());


        while (!bucketlist.isEmpty()) {


            City city = bucketlist.getLast();

            if (city.equals(destination)) return true;

            // remove the city from the bucket list

            // first time visit?
            if (!visited.contains(city)) {

                visited.add(city);

                // add neighbours to the bucket list and
                // remove already visited cities from the list
                bucketlist.addAll(city.getNearby());
                bucketlist.removeAll(visited);

                
            } else {
                // the city has been visited, so remove it from the bucket list
                bucketlist.removeAll(Collections.singleton(city));
            }
        }

        return false;
	}
}
