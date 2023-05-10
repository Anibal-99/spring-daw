package com.trantorinc.springbootlocaldevdocker.controller;

import com.trantorinc.springbootlocaldevdocker.jpa.ClientRepository;
import com.trantorinc.springbootlocaldevdocker.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients/")
@AllArgsConstructor
public class ClientController {
    private ClientRepository clientRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Client> create(@RequestBody Client client) {
        var createdClient = clientRepository.save(client);
        return ResponseEntity.ok(createdClient);
    }
}
