package com.example.backend.repository;

import com.example.backend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.id.hotelId = :hotelId AND r.available = true")
    List<Room> findAvailableRoomsByHotelId(Long hotelId);
}
