package com.apps.geonames.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "geonames")
@Entity
@Getter
@Setter
public class Geoname {
    @Id
    @Column(name = "geonameid")
    private Long geonameId;

    @Column(name = "name")
    private String name;
    @Column(name = "asciiname")
    private String asciiName;
    @Column(name = "alternatenames")
    private String alternateNames;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name="longitude")
    private Double longitude;
    @Column(name = "feature_class")
    private String featureClass;
    @Column(name = "feature_code")
    private String featureCode;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "cc2")
    private String cc2;
    @Column(name = "admin1_code")
    private String admin1Code;
    @Column(name = "admin2_code")
    private String admin2Code;
    @Column(name = "admin3_code")
    private String admin3Code;
    @Column(name = "admin4_code")
    private String admin4Code;
    @Column(name = "population")
    private Long population;
    @Column(name = "elevation")
    private Integer elevation;
    @Column(name = "dem")
    private Double dem;
    @Column(name = "timezone")
    private String timezone;
    @Column(name = "modification_date")
    private Date modificationDate;

}
