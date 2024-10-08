package com.apps.geonames.controller;

import com.apps.geonames.controller.request.BaseRequest;
import com.apps.geonames.controller.request.SearchRequest;
import com.apps.geonames.controller.response.BaseResponse;
import com.apps.geonames.manager.GeonameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeonameController extends BaseController{

    @Autowired
    private GeonameManager manager;

    /**
     *
     * @param q  <----- used for partial query of name column
     * @param longitude <----- used for calculate the nearest
     * @param latitude <----- used for calculate the nearest
     * @param strategy <----- used for determined the calculation for ex: sort it Ascending or Descending
     * @return
     */
    @GetMapping("suggestions")
    public ResponseEntity<BaseResponse> searchNearest(
            @RequestParam String q,
            @RequestParam Double longitude,
            @RequestParam Double latitude,
            @RequestParam String strategy
    ){
        return handleResponse(req-> manager.searchNearest(req), SearchRequest.builder().q(q).longitude(longitude).latitude(latitude).strategy(strategy).build());
    }
}
