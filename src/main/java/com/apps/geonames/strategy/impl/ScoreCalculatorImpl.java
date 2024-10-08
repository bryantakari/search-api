package com.apps.geonames.strategy.impl;

import com.apps.geonames.strategy.ScoreCalculator;
import org.springframework.stereotype.Component;

@Component
public class ScoreCalculatorImpl implements ScoreCalculator {
    private final double MAX_DISTANCE = 20000;
    @Override
    public Double calculate(Double distance) {
        return 1.0 - (distance/MAX_DISTANCE);
    }
}
