package com.example.portfolioId.generator.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(
    name = "portfolio_investor_details",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "phone_number")
    }
)
@Data
public class InvestorDetails {

    @Id
    private UUID portfolioId;

    private String name;

    @Column(name = "phone_number", nullable = false, unique = true)
    private Long phoneNumber;

    private String address;
}
