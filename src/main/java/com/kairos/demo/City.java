package com.kairos.demo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class City {
	
	private String name;
    private Set<City> road = new HashSet<>();

    /**
     * Parameterized constructor
     * @param name
     */
    public City(String name) {
        Objects.requireNonNull(name);
        this.name = name.trim().toUpperCase();
    }
    
    /**
     * Default constructor
     */
    public City() {
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}    

    public City addNearby(City cityMap) {
        road.add(cityMap);
        return this;
    }

    public Set<City> getNearby() {
        return road;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

	@Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
