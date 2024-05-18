package com.example.backend.service;

import com.example.backend.models.Room;
import com.example.backend.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> findAvailableRooms(Long hotelId) {
        return roomRepository.findAvailableRoomsByHotelId(hotelId);
    }




}
