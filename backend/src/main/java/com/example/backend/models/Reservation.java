package com.example.backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "hotel_id", referencedColumnName = "hotelId"),
            @JoinColumn(name = "room_number", referencedColumnName = "roomNumber")
    })
    private Room room;

    @Column(name = "reservation_time")
    private LocalDateTime checkIn;

    public Reservation() {
    }

    public Reservation(Long id, User user, Room room, LocalDateTime checkIn) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
    }

    public Reservation(User user, Room room, LocalDateTime checkIn) {
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }
}
