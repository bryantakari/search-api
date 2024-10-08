package com.apps.geonames.controller.response.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter @Setter
public class SuggestionDTO {

    private String name;
    private Double latitude;
    private Double longitude;
    private Double score;
}
