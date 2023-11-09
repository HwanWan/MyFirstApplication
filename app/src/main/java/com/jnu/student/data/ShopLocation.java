package com.jnu.student.data;

public class ShopLocation {

    private String name;
    private Double latitude;
    private Double longitude;
    private String memo;

    public ShopLocation() {
    }

    public ShopLocation(String name, Double latitude, Double longitude, String memo) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
