package com.apps.geonames.manager;

import com.apps.geonames.controller.request.BaseRequest;
import com.apps.geonames.controller.response.BaseResponse;

public interface GeonameManager {

    public BaseResponse searchNearest(BaseRequest request);
}
