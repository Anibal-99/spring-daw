package com.trantorinc.springbootlocaldevdocker.controller;

import com.trantorinc.springbootlocaldevdocker.jpa.PlaceRepository;
import com.trantorinc.springbootlocaldevdocker.model.Place;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/places/")
@AllArgsConstructor
public class PlaceController {
    private PlaceRepository placeRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Place>> getAll() {
        return ResponseEntity.ok(placeRepository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No place where found with the specified ID"));
        return new ResponseEntity<Place>(place, HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Place> createPlace(@RequestBody Place place) {
        var createdPlace = placeRepository.save(place);
        return ResponseEntity.ok(createdPlace);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Place> updatePlace(@PathVariable Long id, @RequestBody Place place) {
        Place updatedPlace = placeRepository.findById(id)
                .map(p -> {
                    p.setName(place.getName());
                    return placeRepository.save(p);
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "No place with specified ID were found"));
        return new ResponseEntity<Place>(updatedPlace, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
