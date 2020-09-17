package com.kairos.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class CityTest {

    @Test
    public void addNearbyMethod_shouldAddRoadBetweenTwoCities() {
        City city = new City("City1");
        city.addNearby(new City("City2"))
                .addNearby(new City("City3"));

        Set<City> nearbyCities = city.getNearby();
        Assert.assertEquals(2, nearbyCities.size());
        Assert.assertTrue(nearbyCities.contains(new City("City3")));
    }

    @Test
    public void addNearbyMethod_shouldElemenateDuplicates() {
    	City city = new City("City1");
        city.addNearby(new City("City2"))
                .addNearby(new City("City3"))
        		.addNearby(new City("city2"));
        Assert.assertEquals(2, city.getNearby().size());
    }


}
