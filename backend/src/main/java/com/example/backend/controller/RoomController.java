package com.example.backend.controller;

import com.example.backend.models.Room;
import com.example.backend.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{hotelId}")
    public List<Room> findAvailableRooms(@PathVariable Long hotelId) {
        return roomService.findAvailableRooms(hotelId);
    }
}
