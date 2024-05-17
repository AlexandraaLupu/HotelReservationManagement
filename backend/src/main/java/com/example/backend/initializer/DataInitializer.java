package com.example.backend.initializer;

import com.example.backend.models.*;
import com.example.backend.repository.HotelRepository;
import com.example.backend.repository.RoomRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class DataInitializer{
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public DataInitializer(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @PostConstruct
    @Transactional
    public void initData() {
        if (hotelRepository.count() == 0 && roomRepository.count() == 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<HotelJSON>> typeReference = new TypeReference<List<HotelJSON>>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/hotels.json");
            try {
                List<HotelJSON> hotels = objectMapper.readValue(inputStream, typeReference);
                for (HotelJSON hotel : hotels) {
                    // create hotel
                    Hotel hotel1 = new Hotel(hotel.getId(), hotel.getName(), hotel.getLatitude(), hotel.getLongitude());
                    hotelRepository.save(hotel1);
                    for (RoomJSON room : hotel.getRooms()) {
                        // create room
                        Room room1 = new Room(room.getRoomNumber(), room.getType(), room.getPrice(), room.isAvailable(), hotel1);
                        roomRepository.save(room1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
