package com.trantorinc.springbootlocaldevdocker.controller;

import org.springframework.http.MediaType;


import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.trantorinc.springbootlocaldevdocker.jpa.ReservationRepository;
import com.trantorinc.springbootlocaldevdocker.model.Reservation;

import java.util.List;


@RestController
@RequestMapping("/api/reservations/")
@AllArgsConstructor
public class ReservationController {
    private ReservationRepository reservationRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(reservationRepository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No reservation where found with the specified ID"));
        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        var createdReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationRepository.findById(id)
                .map(r -> {
                    r.setTitle(reservation.getTitle());
                    r.setReason(reservation.getReason());
                    r.setTime(reservation.getTime());
                    r.setAmmount(reservation.getAmmount());
                    r.setState(reservation.getState());
                    r.setClient(reservation.getClient());
                    return reservationRepository.save(r);
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "No place with specified ID were found"));
        return new ResponseEntity<Reservation>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
