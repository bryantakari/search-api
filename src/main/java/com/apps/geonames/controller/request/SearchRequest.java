package com.apps.geonames.controller.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchRequest extends BaseRequest{
    private String q;
    private Double longitude;
    private Double latitude;
    private String strategy;
}
