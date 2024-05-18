package com.example.backend.controller;

import com.example.backend.models.Hotel;
import com.example.backend.models.Reservation;
import com.example.backend.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsForUser(@PathVariable Long userId) {
        List<Reservation> reservations = reservationService.getReservationsForUser(userId);
            return ResponseEntity.ok(reservations);
    }


    @PostMapping("/book")
    public ResponseEntity<Reservation> bookRoom(@RequestBody Reservation reservation) {
        Reservation bookedReservation = reservationService.bookRoom(reservation);
        return new ResponseEntity<>(bookedReservation, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<Reservation> changeReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        Reservation changedReservation = reservationService.changeReservation(id, updatedReservation);
        if (changedReservation != null) {
            return ResponseEntity.ok(changedReservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Reservation> getAllReservation() {
        return reservationService.getAllReservations();
    }
}
