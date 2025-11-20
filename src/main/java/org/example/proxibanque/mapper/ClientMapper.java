package org.example.proxibanque.mapper;

import org.example.proxibanque.dto.response.ClientResponse;
import org.example.proxibanque.model.entity.Client;

public class ClientMapper {

    public static ClientResponse clientToDto(Client client) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setAdresse(client.getAdresse());
        clientResponse.setNom(client.getNom());
        clientResponse.setPrenom(client.getPrenom());
        clientResponse.setTelephone(client.getTelephone());
        clientResponse.setCodePostal(client.getCodePostal());
        clientResponse.setAgency(client.getAgency().getId());
        clientResponse.setVille(client.getVille());
        return clientResponse;
    }

}
