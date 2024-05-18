package com.example.backend.models;

import java.util.List;

// helper class for the json file
public class HotelJSON {
    private long id;

    private String name;

    private double latitude;

    private double longitude;

    private List<RoomJSON> rooms;

    public HotelJSON(long id, String name, double latitude, double longitude, List<RoomJSON> rooms) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rooms = rooms;
    }

    public HotelJSON() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<RoomJSON> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomJSON> rooms) {
        this.rooms = rooms;
    }
}
