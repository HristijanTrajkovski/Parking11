package com.example.parking;

public class Parking {
    String parkName;
    String parkCity;
    int parkSpaces;
    int takenSpaces;
    double lat;
    double lng;

    public Parking(){

    }

    public Parking (String name, String city, int brojP, int zafateni,double lat, double lng){
        this.parkName = name;
        this.parkCity = city;
        this.parkSpaces = brojP;
        this.takenSpaces = zafateni;
        this.lat = lat;
        this.lng = lng;

    }

    public String getParkName() {

        return parkName;
    }

    public void setParkName(String parkName) {

        this.parkName = parkName;
    }

    public String getParkCity() {

        return parkCity;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLat() {
        return lat;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setParkCity(String parkCity) {

        this.parkCity = parkCity;
    }

    public void setParkSpaces(int parkSpaces) {

        this.parkSpaces = parkSpaces;
    }

    public int getParkSpaces() {

        return parkSpaces;
    }
    public void setTakenSpaces(int takenSpaces) {
        this.takenSpaces = takenSpaces;

    }

    public int getTakenSpaces() {

        return takenSpaces;
    }


}
