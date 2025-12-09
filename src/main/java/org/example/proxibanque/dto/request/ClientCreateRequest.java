package org.example.proxibanque.dto.request;

public record ClientCreateRequest (String nom, String prenom, String adresse ,Integer codePostal, String ville,
                                   String telephone, String agencyId) {

}
