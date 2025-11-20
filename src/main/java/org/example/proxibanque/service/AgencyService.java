package org.example.proxibanque.service;

import org.example.proxibanque.dto.request.AgencyCreateRequest;
import org.example.proxibanque.model.entity.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyService {

    Agency createAgency(AgencyCreateRequest agency);

    List<Agency> getAllAgencies();

    Optional<Agency> getAgency(String id);

}
