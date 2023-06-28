package com.trantorinc.springbootlocaldevdocker.controller;
import com.trantorinc.springbootlocaldevdocker.model.views.ClientDto;
import com.trantorinc.springbootlocaldevdocker.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/clients/")
@AllArgsConstructor
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients() {
        return new ResponseEntity<>(clientService.findAllClients(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto client) {
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateClient(@PathVariable Long id, @RequestBody ClientDto client) {
        clientService.updateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
