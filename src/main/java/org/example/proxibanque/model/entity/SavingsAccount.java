package org.example.proxibanque.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long accountNumber;

    protected Long solde = 0L;

    protected OffsetDateTime created_at = OffsetDateTime.now();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    private Double interestRate = 0.03;

    public boolean deposit(Long value) {
        this.solde += value;
        return true;
    }

    public boolean withdraw(Long value) {
        if (this.solde - value < 0) {
            return false;
        }
        this.solde -= value;
        return true;
    }
}
