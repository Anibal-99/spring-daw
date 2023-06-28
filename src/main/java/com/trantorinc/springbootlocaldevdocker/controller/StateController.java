package com.trantorinc.springbootlocaldevdocker.controller;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trantorinc.springbootlocaldevdocker.model.views.StateDto;
import com.trantorinc.springbootlocaldevdocker.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/states/")
@AllArgsConstructor
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<StateDto>> getStates() {
        return new ResponseEntity<>(stateService.findAllStates(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StateDto> getStateById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(stateService.getStateById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StateDto> createState(@RequestBody StateDto State) {
        return new ResponseEntity<>(stateService.createState(State), HttpStatus.CREATED);
    }
}
