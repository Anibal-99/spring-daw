package com.trantorinc.springbootlocaldevdocker.service;
import com.trantorinc.springbootlocaldevdocker.model.views.ClientDto;
import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientDto clientDto);
    List<ClientDto> findAllClients();
    void deleteClient(Long id);
    ClientDto getClientById(Long id);
    void updateClient(Long id, ClientDto clientDto);
}
