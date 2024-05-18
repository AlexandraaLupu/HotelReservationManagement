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
        double radiusInMeters = radiusInKm * 1000;

        // https://stackoverflow.com/questions/1253499/simple-calculations-for-working-with-lat-lon-and-km-distance
        double userX = userLatitude * 111000;
        double userY = userLongitude * 111000 * Math.cos(Math.toRadians(userLatitude));

        List<Hotel> nearbyHotels = new ArrayList<>();

        for (Hotel hotel : allHotels) {
            double hotelX = hotel.getLatitude() * 111000;
            double hotelY = hotel.getLongitude() * 111000 * Math.cos(Math.toRadians(hotel.getLatitude()));

            double distance = Math.sqrt(Math.pow(userX - hotelX, 2) + Math.pow(userY - hotelY, 2));

            if (distance <= radiusInMeters) {
                nearbyHotels.add(hotel);
            }
        }

        return nearbyHotels;
    }

}
