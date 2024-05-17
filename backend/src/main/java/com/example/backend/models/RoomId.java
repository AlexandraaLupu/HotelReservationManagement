package com.example.backend.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RoomId implements Serializable {
    private Long hotelId;
    private int roomNumber;

    public RoomId(Long hotelId, int roomNumber) {
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
    }

    public RoomId() {

    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
