package org.example.proxibanque.service;

import org.example.proxibanque.dto.request.AgencyCreateRequest;
import org.example.proxibanque.dto.response.AgencyResponse;
import org.example.proxibanque.dto.response.ClientResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AgencyService {

    AgencyResponse createAgency(AgencyCreateRequest agency);

    List<AgencyResponse> getAllAgencies();

    AgencyResponse getAgency(String id);

    Set<ClientResponse> getAllClients(String id);

}
