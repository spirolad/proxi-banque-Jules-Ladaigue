package org.example.proxibanque.mapper;

import org.example.proxibanque.dto.response.AgencyResponse;
import org.example.proxibanque.model.entity.Agency;

public class AgencyMapper {

    public static AgencyResponse agencyResponse(Agency agency) {
        AgencyResponse agencyResponse = new AgencyResponse();
        agencyResponse.setId(agency.getId());
        agencyResponse.setCreatedAt(agency.getCreatedAt());
        return agencyResponse;
    }

}
