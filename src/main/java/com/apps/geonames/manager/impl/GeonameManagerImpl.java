package com.apps.geonames.manager.impl;

import com.apps.geonames.controller.request.BaseRequest;
import com.apps.geonames.controller.request.SearchRequest;
import com.apps.geonames.controller.response.BaseResponse;
import com.apps.geonames.controller.response.SuggestionResponse;
import com.apps.geonames.controller.response.dto.SuggestionDTO;
import com.apps.geonames.manager.GeonameManager;
import com.apps.geonames.repository.GeonamesRepository;
import com.apps.geonames.repository.model.Geoname;
import com.apps.geonames.strategy.SuggestionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GeonameManagerImpl implements GeonameManager {
    @Autowired
    private GeonamesRepository repository;

    @Autowired
    private Map<String, SuggestionStrategy> strategyMap;


    @Override
    public BaseResponse searchNearest(BaseRequest request) {
        SearchRequest searchRequest = (SearchRequest) request;
        SuggestionResponse response = new SuggestionResponse();


        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());

        List<Geoname> modelList = repository.findByNameContainingIgnoreCase(searchRequest.getQ());

        List<SuggestionDTO> dtos = strategyMap.get(searchRequest.getStrategy()).applyStrategy(searchRequest,modelList);

        response.setSuggestions(dtos);
        return response;
    }

}
