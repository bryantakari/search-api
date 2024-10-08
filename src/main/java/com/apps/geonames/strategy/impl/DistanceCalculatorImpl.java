package com.apps.geonames.strategy.impl;

import com.apps.geonames.strategy.DistanceCalculator;
import org.springframework.stereotype.Component;

@Component
public class DistanceCalculatorImpl implements DistanceCalculator {
    private final int radius = 6371;
    @Override
    public Double calculate(Double reqlat, Double reqlon, Double currLat, Double currLon) {


        Double latDist = Math.toRadians(currLat - reqlat);
        Double lonDist = Math.toRadians(currLon - reqlon);

        Double a = Math.sin(latDist/2) * Math.sin(latDist/2)
                + Math.cos(Math.toRadians(reqlat)) * Math.cos(Math.toRadians(currLat))
                * Math.sin(lonDist/2) * Math.sin(lonDist/2);
        Double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));

        return radius * c;
    }
}
