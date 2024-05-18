package com.example.backend.controller;

import com.example.backend.models.Hotel;
import com.example.backend.models.Room;
import com.example.backend.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

//    @GetMapping
//    public List<Hotel> getAllHotels() {
//        return hotelService.getAllHotels();
//    }

    @GetMapping
    public List<Hotel> getNearbyHotels(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius) {
        if (radius == 0)
            return hotelService.getAllHotels();
        return hotelService.findNearbyHotels(latitude, longitude, radius);
    }

}
