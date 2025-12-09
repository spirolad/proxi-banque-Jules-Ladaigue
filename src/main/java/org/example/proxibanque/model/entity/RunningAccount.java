package org.example.proxibanque.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class RunningAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    private Long solde = 0L;

    private OffsetDateTime created_at = OffsetDateTime.now();

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    private Long minimumBalance = 1000L;

    public RunningAccount(Long initialAmount) {
        this.solde = initialAmount;
    }

    public boolean deposit(Long value) {
        this.solde += value;
        return true;
    }

    public boolean withdraw(Long value) {
        if (this.solde - value < minimumBalance) {
            return false;
        }
        this.solde -= value;
        return true;
    }
}
