package org.example.proxibanque.service;

import org.example.proxibanque.dto.request.AgencyCreateRequest;
import org.example.proxibanque.model.entity.Agency;
import org.example.proxibanque.model.entity.Client;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AgencyService {

    Agency createAgency(AgencyCreateRequest agency);

    List<Agency> getAllAgencies();

    Optional<Agency> getAgency(String id);

    Set<Client> getAllClients(String id);

}
