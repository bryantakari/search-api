package com.apps.geonames.strategy;

public interface DistanceCalculator {
    Double calculate(Double reqlat, Double reqlon, Double currLat,Double currLon);
}
