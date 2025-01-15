package com.example.postgresqlprogram.model;

public class Location {
    private Long idLocation;
    private String cityName;
    private String streetName;
    private Integer streetNumber;
    private String locationType;

    public Long getIdLocation() { return idLocation; }
    public void setIdLocation(Long idLocation) { this.idLocation = idLocation; }

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public String getStreetName() { return streetName; }
    public void setStreetName(String streetName) { this.streetName = streetName; }

    public Integer getStreetNumber() { return streetNumber; }
    public void setStreetNumber(Integer streetNumber) { this.streetNumber = streetNumber; }

    public String getLocationType() { return locationType; }
    public void setLocationType(String locationType) { this.locationType = locationType; }
}
