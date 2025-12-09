package org.example.proxibanque.dto.response;

public record ClientResponse(String nom, String prenom, String adresse, Integer codePostal, String ville,
                             String telephone, String agency, Long runningAccountAmount) {

}
