package com.example.backend.controller;

import com.example.backend.models.Room;
import com.example.backend.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

//    @GetMapping
//    public List<Room> getAllRooms() {
//        return roomService.getAllRooms();
//    }

    @GetMapping("/{hotelId}")
    public List<Room> findAvailableRooms(@PathVariable Long hotelId) {
        return roomService.findAvailableRooms(hotelId);
    }
}
