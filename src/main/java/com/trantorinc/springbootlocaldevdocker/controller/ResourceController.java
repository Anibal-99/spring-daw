package com.trantorinc.springbootlocaldevdocker.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  // @GetMapping("/{id}")
  // private ResponseEntity<Resource> getRoot(@PathVariable Integer id) {
  // return ResponseEntity.ok(new Resource(id, "samuca"));
  // }
}