package com.example.backend.service;

import com.example.backend.models.Reservation;
import com.example.backend.models.Room;
import com.example.backend.repository.ReservationRepository;
import com.example.backend.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationService, RoomRepository roomRepository) {
        this.reservationRepository = reservationService;
        this.roomRepository = roomRepository;
    }

    public List<Reservation> getReservationsForUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    public Reservation bookRoom(Reservation reservation) {
        // i set the check in time to be the date of tomorrow at 12:00
        LocalDateTime checkInTime = LocalDate.now().plusDays(1).atTime(12, 0);
        reservation.setCheckIn(checkInTime);
        Room bookedRoom = reservation.getRoom();
        bookedRoom.setAvailable(false);
        roomRepository.save(bookedRoom);
        return reservationRepository.save(reservation);
    }

    public boolean cancelReservation(Long id) {
        Reservation canceledReservation = reservationRepository.findById(id).orElse(null);
        if (canceledReservation != null) {
            if (isModificationAllowed(canceledReservation.getCheckIn())) {
                Room canceledRoom = canceledReservation.getRoom();
                canceledRoom.setAvailable(true);
                roomRepository.save(canceledRoom);
                reservationRepository.deleteById(id);
                return true;
            } else {
                return false; // reservation can only be canceled at least two hours before check-in
            }
        }
        return false;
    }

    public Reservation changeReservation(Long id, Reservation updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);
        if (existingReservation != null) {
            if (isModificationAllowed(existingReservation.getCheckIn())) {
                updatedReservation.setId(id);
                return reservationRepository.save(updatedReservation);
               }
            else {
                return null;
            }
        }
        return null;
    }

    // method for checking if there are at least 2 hours before the check in
    private boolean isModificationAllowed(LocalDateTime checkInTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twoHoursBeforeCheckIn = checkInTime.minusHours(2);
        return now.isBefore(twoHoursBeforeCheckIn);
    }
}
