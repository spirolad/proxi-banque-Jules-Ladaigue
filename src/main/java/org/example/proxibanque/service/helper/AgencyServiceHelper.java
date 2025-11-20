package org.example.proxibanque.service.helper;

import org.example.proxibanque.model.entity.Agency;

import java.util.Optional;

public interface AgencyServiceHelper {

    Optional<Agency> getAgency(String id);

}
