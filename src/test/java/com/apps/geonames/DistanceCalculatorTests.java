package com.apps.geonames;

import com.apps.geonames.strategy.impl.DistanceCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DistanceCalculatorTests {
    private DistanceCalculatorImpl distanceCalculator;

    @BeforeEach
    public void setUp() {
        distanceCalculator = new DistanceCalculatorImpl();
    }

    @Test
    public void testCalculateDistanceSameLocation() {

        Double lat = 10.0;
        Double lon = 20.0;


        Double distance = distanceCalculator.calculate(lat, lon, lat, lon);


        assertEquals(0.0, distance, 0.001);
    }

    @Test
    public void testCalculateDistanceDifferentLocations() {

        Double lat1 = 10.0;
        Double lon1 = 20.0;
        Double lat2 = 15.0;
        Double lon2 = 25.0;


        Double distance = distanceCalculator.calculate(lat1, lon1, lat2, lon2);


        assertNotNull(distance);
        assertTrue(distance > 0);
    }

    @Test
    public void testCalculateDistanceNegativeCoordinates() {

        Double lat1 = -10.0;
        Double lon1 = -20.0;
        Double lat2 = -15.0;
        Double lon2 = -25.0;

        Double distance = distanceCalculator.calculate(lat1, lon1, lat2, lon2);

        assertNotNull(distance);
        assertTrue(distance > 0);
    }
}
