package com.trantorinc.springbootlocaldevdocker.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trantorinc.springbootlocaldevdocker.jpa.ResourceRepository;
import com.trantorinc.springbootlocaldevdocker.model.Resource;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/resources/")
@AllArgsConstructor
public class ResourceController {
  private ResourceRepository resourceRepository;

  @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<List<Resource>> getAll() {
    return ResponseEntity.ok(resourceRepository.findAll());
  }

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<Resource> create(@RequestBody Resource resource) {
    var createdResource = resourceRepository.save(resource);
    return ResponseEntity.ok(createdResource);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
    Resource resource = resourceRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No resources with specified ID were found"));
    return new ResponseEntity<Resource>(resource, HttpStatus.OK);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Resource> updateResource(@PathVariable Long id, @RequestBody Resource resource) {
    Resource updatedResource = resourceRepository.findById(id)
        .map(r -> {
          r.setName(resource.getName());
          r.setDescription(resource.getDescription());
          return resourceRepository.save(r);
        })
        .orElseThrow();
    return new ResponseEntity<Resource>(updatedResource, HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
    resourceRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}