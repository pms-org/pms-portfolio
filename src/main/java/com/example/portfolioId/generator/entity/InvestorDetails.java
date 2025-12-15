package com.example.portfolioId.generator.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "portfolio_investor_details")
@Data
public class InvestorDetails {

    @Id
    private UUID portfolioId;

    private String name;
    @Column(name = "phone_number") 	
    private Long phoneNumber;
    private String address;
}
