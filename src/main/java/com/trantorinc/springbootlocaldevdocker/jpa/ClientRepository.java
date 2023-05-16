package com.trantorinc.springbootlocaldevdocker.jpa;

import com.trantorinc.springbootlocaldevdocker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
  Iterable<Client> findAllByName(String n);
  Iterable<Client> findAllByNameContainsIgnoreCase(String n);
}