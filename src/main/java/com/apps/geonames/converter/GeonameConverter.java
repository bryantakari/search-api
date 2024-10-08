package com.apps.geonames.converter;

import com.apps.geonames.controller.response.dto.SuggestionDTO;
import com.apps.geonames.repository.model.Geoname;

public class GeonameConverter {

    public static SuggestionDTO convertGeonameToSuggestDTO(Geoname model,Double score){
        return SuggestionDTO.builder()
                .name(model.getName())
                .latitude(model.getLatitude())
                .longitude(model.getLongitude())
                .score(score).build();
    }
}
