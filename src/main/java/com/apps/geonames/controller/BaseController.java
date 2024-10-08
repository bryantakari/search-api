package com.apps.geonames.controller;

import com.apps.geonames.controller.request.BaseRequest;
import com.apps.geonames.controller.response.BaseResponse;
import com.apps.geonames.exception.GeonameException;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    protected ResponseEntity<BaseResponse> handleResponse(ServiceMethod serviceMethod,BaseRequest request){
        BaseResponse response = new BaseResponse();
        try{
            response = serviceMethod.run(request);
            return ResponseEntity.status(200).body(response);
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("ERROR!@#");
            return ResponseEntity.status(500).body(response);
        }
    }

    @FunctionalInterface
    protected interface ServiceMethod{
        BaseResponse run(BaseRequest request) throws GeonameException;
    }
}
