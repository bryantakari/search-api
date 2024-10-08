package com.apps.geonames.controller.response;

import com.apps.geonames.controller.response.dto.SuggestionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class SuggestionResponse extends BaseResponse{
    private List<SuggestionDTO> suggestions;
}
