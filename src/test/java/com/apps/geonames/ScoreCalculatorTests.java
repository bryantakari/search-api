package com.apps.geonames;

import com.apps.geonames.strategy.impl.ScoreCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCalculatorTests {

    private ScoreCalculatorImpl scoreCalculator;

    @BeforeEach
    public void setUp() {
        scoreCalculator = new ScoreCalculatorImpl(); // Instantiate the class
    }

    @Test
    public void testCalculateScoreWithZeroDistance() {
        // Arrange
        Double distance = 0.0;

        // Act
        Double score = scoreCalculator.calculate(distance);

        // Assert
        assertEquals(1.0, score, 0.001); // Expect score to be 1.0 when distance is 0
    }
}
