package com.trantorinc.springbootlocaldevdocker.controller;

import com.trantorinc.springbootlocaldevdocker.model.views.ResourceDto;
import com.trantorinc.springbootlocaldevdocker.service.ResourceService;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/resources/")
@AllArgsConstructor
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public ResponseEntity<List<ResourceDto>> getResources() {
        return new ResponseEntity<>(resourceService.findAllResources(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResourceDto> getResourceById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(resourceService.getResourceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResourceDto> createResource(@RequestBody ResourceDto resource) {
        return new ResponseEntity<>(resourceService.createResource(resource), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteResource(@PathVariable("id") Long id) {
        resourceService.deleteResource(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateResource(@PathVariable Long id, @RequestBody ResourceDto resource) {
        resourceService.updateResource(id, resource);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
