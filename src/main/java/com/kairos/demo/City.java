package com.kairos.demo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public class City {
	 private String name;

	    private Set<City> road = new HashSet<>();

	    private City(String name) {
	        Objects.requireNonNull(name);
	        this.name = name.trim().toUpperCase();
	    }

	    private City() {
	    }

	    public static City build(String name) {
	        return new City(name);
	    }

	    public String prettyPrint() {
	        return road
	                .stream()
	                .map(City::getName)
	                .collect(Collectors.joining(","));
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

	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
	    public int hashCode() {
	        return Objects.hash(name);
	    }
}
