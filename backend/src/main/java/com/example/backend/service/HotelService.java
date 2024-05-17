package com.example.backend.service;

import com.example.backend.models.Hotel;
import com.example.backend.models.Room;
import com.example.backend.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    public List<Hotel> findNearbyHotels(double userLatitude, double userLongitude, double radiusInKm) {
        List<Hotel> allHotels = hotelRepository.findAll();

        // Calculate the radius in meters
        double radiusInMeters = radiusInKm * 1000;

        // Calculate the user position in meters
        double userX = userLatitude * 111000; // 1 degree latitude is approximately 111km
        double userY = userLongitude * 111000 * Math.cos(Math.toRadians(userLatitude)); // 1 degree longitude varies depending on the latitude

        // Create a list to store nearby hotels
        List<Hotel> nearbyHotels = new ArrayList<>();

        // Iterate through all hotels to find nearby ones
        for (Hotel hotel : allHotels) {
            // Calculate the hotel's position in meters
            double hotelX = hotel.getLatitude() * 111000;
            double hotelY = hotel.getLongitude() * 111000 * Math.cos(Math.toRadians(hotel.getLatitude()));

            // Calculate the distance between the user and the hotel using the distance formula
            double distance = Math.sqrt(Math.pow(userX - hotelX, 2) + Math.pow(userY - hotelY, 2));

            // Check if the hotel is within the specified radius
            if (distance <= radiusInMeters) {
                nearbyHotels.add(hotel);
            }
        }

        return nearbyHotels;
    }

}
