package com.example.portfolioId.generator.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.portfolioId.generator.entity.PortfolioOutbox;

public interface PortfolioOutboxRepository
        extends JpaRepository<PortfolioOutbox, UUID> {

    List<PortfolioOutbox> findByStatus(String status);
}

