package com.example.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name="rooms")
public class Room {
    @EmbeddedId
    private RoomId id;

    @Column(name = "type")
    private int type;

    @Column(name = "price")
    private int price;

    @Column(name = "available")
    private boolean available;

    public Room() {
    }

    public Room(int roomNumber, int type, int price, boolean available, Hotel hotel) {
        this.id = new RoomId(hotel.getId(), roomNumber);
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public RoomId getId() {
        return id;
    }

    public void setId(RoomId id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

