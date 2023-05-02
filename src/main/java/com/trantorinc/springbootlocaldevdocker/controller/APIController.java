package com.trantorinc.springbootlocaldevdocker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trantorinc.springbootlocaldevdocker.model.Resource;

@RestController
@RequestMapping("/api")
public class APIController {
    @GetMapping("/")
    private ResponseEntity<Resource> getRoot() {
        return ResponseEntity.ok(new Resource(1, "samuca"));
    }
}