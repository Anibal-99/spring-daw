package com.trantorinc.springbootlocaldevdocker.service.impl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trantorinc.springbootlocaldevdocker.jpa.ClientRepository;
import com.trantorinc.springbootlocaldevdocker.model.Client;
import com.trantorinc.springbootlocaldevdocker.model.views.ClientDto;
import com.trantorinc.springbootlocaldevdocker.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ReservationServiceImpl reservationService;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Client not found - id:";
 

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        clientRepository.save(client);
        clientDto = modelMapper.map(client, ClientDto.class);
        log.info(String.format("Client %s created successfully", client.getName()));
        return clientDto;
    }

    @Override
    public List<ClientDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClient(Long id) {
        this.reservationService.deleteReservationByIdClient(id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        clientRepository.delete(client);
        log.info("Client deleted successfully");
    }

    @Override
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    public void updateClient(Long id, ClientDto ClientDto) {
        Client clientToUpdate = modelMapper.map(ClientDto, Client.class);
        clientToUpdate.setId(id);
        clientRepository.save(clientToUpdate);
        log.info(String.format("Client %s updated successfully", clientToUpdate.getName()));
    }
    
}
