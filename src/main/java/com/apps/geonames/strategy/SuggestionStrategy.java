package com.apps.geonames.strategy;

import com.apps.geonames.controller.request.SearchRequest;
import com.apps.geonames.controller.response.dto.SuggestionDTO;
import com.apps.geonames.repository.model.Geoname;

import java.util.List;

public interface SuggestionStrategy {
    public List<SuggestionDTO> applyStrategy(SearchRequest searchRequest, List<Geoname> geonames);
}
