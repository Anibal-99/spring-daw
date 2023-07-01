package com.trantorinc.springbootlocaldevdocker.controller;
import com.trantorinc.springbootlocaldevdocker.model.views.PlaceDto;
import com.trantorinc.springbootlocaldevdocker.service.PlaceService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/places/")
@AllArgsConstructor
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceDto>> getPlaces() {
        return new ResponseEntity<>(placeService.findAllPlaces(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlaceDto> getPlaceById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(placeService.getPlaceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlaceDto> createPlace(@RequestBody PlaceDto place) {
        return new ResponseEntity<>(placeService.createPlace(place), HttpStatus.CREATED);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlace(@PathVariable("id") Long id) {
        placeService.deletePlace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePlace(@PathVariable Long id, @RequestBody PlaceDto place) {
        placeService.updatePlace(id, place);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
