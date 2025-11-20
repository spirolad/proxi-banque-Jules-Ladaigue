package org.example.proxibanque.model.repository;

import org.example.proxibanque.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client getClientById(Long id);
}
