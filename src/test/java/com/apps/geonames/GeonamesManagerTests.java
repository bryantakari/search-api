package com.apps.geonames;

import com.apps.geonames.controller.request.SearchRequest;
import com.apps.geonames.controller.response.SuggestionResponse;
import com.apps.geonames.controller.response.dto.SuggestionDTO;
import com.apps.geonames.manager.impl.GeonameManagerImpl;
import com.apps.geonames.repository.GeonamesRepository;
import com.apps.geonames.repository.model.Geoname;
import com.apps.geonames.strategy.SuggestionStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GeonamesManagerTests {
    @InjectMocks
    private GeonameManagerImpl manager;

    @Mock
    private GeonamesRepository repository; // Replace with your actual repository interface

    @Mock
    private Map<String, SuggestionStrategy> strategyMap;

    @Mock
    private SuggestionStrategy suggestionStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testServiceSearch() {
        SearchRequest request = SearchRequest.builder().q("Austin").longitude(34.052235).latitude(-74.005974).strategy("AscendingStrategy").build();

        Geoname geo1 = new Geoname();
        geo1.setName("Austin US");
        geo1.setLatitude(10.0);
        geo1.setLongitude(20.0);
        Geoname geo2 = new Geoname();
        geo2.setName("Austin England");
        geo2.setLatitude(15.0);
        geo2.setLongitude(25.0);

        when(repository.findByNameContainingIgnoreCase(request.getQ()))
                .thenReturn(Arrays.asList(geo1, geo2));

        when(strategyMap.get(request.getStrategy())).thenReturn(suggestionStrategy);


        SuggestionDTO dto1 = SuggestionDTO.builder().name("Austin US").score(1.0).latitude(10.0).longitude(20.0).build();
        SuggestionDTO dto2 = SuggestionDTO.builder().name("Austin England").score(0.5).latitude(15.0).longitude(25.0).build();
        when(suggestionStrategy.applyStrategy(request, Arrays.asList(geo1, geo2)))
                .thenReturn(Arrays.asList(dto1, dto2));

        SuggestionResponse response = (SuggestionResponse) manager.searchNearest(request);

        assertEquals(HttpStatus.OK.value(), response.getCode());
        assertEquals(2, response.getSuggestions().size());
    }
}
