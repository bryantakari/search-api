package com.apps.geonames.strategy.impl;

import com.apps.geonames.controller.request.SearchRequest;
import com.apps.geonames.controller.response.dto.SuggestionDTO;
import com.apps.geonames.converter.GeonameConverter;
import com.apps.geonames.repository.model.Geoname;
import com.apps.geonames.strategy.DistanceCalculator;
import com.apps.geonames.strategy.ScoreCalculator;
import com.apps.geonames.strategy.SuggestionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component("DescendingStrategy")
public class DescendingSuggestionStrategy implements SuggestionStrategy {
    @Autowired
    private DistanceCalculator distCalculator;
    @Autowired
    private ScoreCalculator scoreCalculator;
    @Override
    public List<SuggestionDTO> applyStrategy(SearchRequest searchRequest, List<Geoname> geonames) {
        return geonames.stream().map(geo->{
            Double dist = distCalculator.calculate(searchRequest.getLatitude(), searchRequest.getLongitude(), geo.getLatitude(), geo.getLongitude());
            Double score = scoreCalculator.calculate(dist);
            return GeonameConverter.convertGeonameToSuggestDTO(geo,score);

        }).filter(dto -> dto.getScore() > 0).sorted((g1,g2)-> Double.compare(g2.getScore(),g1.getScore())).collect(Collectors.toList());
    }
}
