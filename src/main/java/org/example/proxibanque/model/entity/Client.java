package org.example.proxibanque.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;
    private String adresse;
    private Integer codePostal;
    private String ville;
    private Integer telephone;

    @OneToOne(mappedBy = "client")
    private RunningAccount runningAccount;

    @OneToOne(mappedBy = "client")
    private SavingsAccount savingsAccount;

    public Client(String nom, String prenom, String adresse, Integer codePostal, String ville, Integer telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
    }

}
