# kairostech-test-service

This is a test code for finding whether the destination city is reachable from origin city

# SetUp:
~~~~
mvn clean install
~~~~

# Run:
~~~~
mvn spring-boot:run
~~~~
# Test:
~~~~
mvn clean test
~~~~

# Endpoint access:

~~~~
http://localhost:8080/connected?origin=city1&destination=city2
~~~~

Given, a predefined city connections in cities.txt,

use the above endpoint to find two cities connected or not.

