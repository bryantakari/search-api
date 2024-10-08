package com.apps.geonames;

import com.apps.geonames.controller.request.SearchRequest;
import com.apps.geonames.controller.response.dto.SuggestionDTO;
import com.apps.geonames.repository.model.Geoname;
import com.apps.geonames.strategy.DistanceCalculator;
import com.apps.geonames.strategy.ScoreCalculator;
import com.apps.geonames.strategy.impl.AscendingSuggestionStrategy;
import com.apps.geonames.strategy.impl.DescendingSuggestionStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DescendingStrategyTests {
    @InjectMocks
    private DescendingSuggestionStrategy descendingStrategy;

    @Mock
    private DistanceCalculator distCalculator;

    @Mock
    private ScoreCalculator scoreCalculator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testApplyStrategy() {
        // Arrange
        SearchRequest request = SearchRequest.builder().longitude(20.0).latitude(10.0).build();


        Geoname geo1 = new Geoname();
        geo1.setName("Austin US");
        geo1.setLatitude(10.0);
        geo1.setLongitude(20.0);
        Geoname geo2 = new Geoname();
        geo2.setName("Austin England");
        geo2.setLatitude(15.0);
        geo2.setLongitude(25.0);

        when(distCalculator.calculate(10.0, 20.0, 10.0, 20.0)).thenReturn(0.0);
        when(scoreCalculator.calculate(0.0)).thenReturn(1.0);
        when(distCalculator.calculate(10.0, 20.0, 15.0, 25.0)).thenReturn(5.0);
        when(scoreCalculator.calculate(5.0)).thenReturn(0.5);


        List<SuggestionDTO> suggestions = descendingStrategy.applyStrategy(request, Arrays.asList(geo1, geo2));


        assertEquals(2, suggestions.size());
        assertEquals("Austin US", suggestions.get(0).getName());
        assertEquals("Austin England", suggestions.get(1).getName());
        assertEquals(1.0, suggestions.get(0).getScore());
        assertEquals(0.5, suggestions.get(1).getScore());
    }

    @Test
    public void testApplyStrategyWithNoPositiveScores() {

        SearchRequest request = SearchRequest.builder().longitude(20.0).latitude(10.0).build();


        Geoname geo1 = new Geoname();
        geo1.setName("Austin US");
        geo1.setLatitude(10.0);
        geo1.setLongitude(20.0);
        Geoname geo2 = new Geoname();
        geo1.setName("Austin England");
        geo1.setLatitude(15.0);
        geo1.setLongitude(25.0);

        when(distCalculator.calculate(10.0, 20.0, 10.0, 20.0)).thenReturn(0.0);
        when(scoreCalculator.calculate(0.0)).thenReturn(0.0);
        when(distCalculator.calculate(10.0, 20.0, 15.0, 25.0)).thenReturn(5.0);
        when(scoreCalculator.calculate(5.0)).thenReturn(0.0);


        List<SuggestionDTO> suggestions = descendingStrategy.applyStrategy(request, Arrays.asList(geo1, geo2));


        assertTrue(suggestions.isEmpty());
    }
}
