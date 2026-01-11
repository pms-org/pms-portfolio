package com.example.portfolioId.generator.entity;

import java.time.LocalDateTime;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "portfolio_outbox")
@Data
public class PortfolioOutbox {

    @Id
    private UUID id;

    private UUID aggregateId;        // portfolioId

    @Column(columnDefinition = "jsonb")
    private String payload;

    private String status;           // NEW

    private LocalDateTime createdAt;
}

