package com.example.portfolioId.generator.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.portfolioId.generator.dto.PortfolioRequest;
import com.example.portfolioId.generator.dto.PortfolioResponse;
import com.example.portfolioId.generator.entity.InvestorDetails;
import com.example.portfolioId.generator.entity.PortfolioOutbox;
import com.example.portfolioId.generator.exception.PortfolioCreationException;
import com.example.portfolioId.generator.repository.InvestorDetailsRepository;
import com.example.portfolioId.generator.repository.PortfolioOutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioService {

    private final InvestorDetailsRepository investorRepo;
    private final PortfolioOutboxRepository outboxRepo;
    private final ObjectMapper objectMapper;
    @Transactional
    public PortfolioResponse createPortfolio(PortfolioRequest req) {

        log.info("Service received phoneNumber={}", req.getPhoneNumber());

        if (investorRepo.existsByPhoneNumber(req.getPhoneNumber())) {
            log.warn("Duplicate phone number attempt: {}", req.getPhoneNumber());
            throw new PortfolioCreationException(
                "Portfolio already exists for this phone number"
            );
        }

        try {
            UUID newId = UUID.randomUUID();

            InvestorDetails investor = new InvestorDetails();
            investor.setPortfolioId(newId);
            investor.setName(req.getName());
            investor.setPhoneNumber(req.getPhoneNumber());
            investor.setAddress(req.getAddress());

            investorRepo.save(investor);


            PortfolioOutbox outbox = new PortfolioOutbox();
            outbox.setId(UUID.randomUUID());
            outbox.setAggregateId(newId);
            outbox.setPayload(objectMapper.writeValueAsString(investor));
            outbox.setStatus("NEW");
            outbox.setCreatedAt(LocalDateTime.now());

            outboxRepo.save(outbox);

            log.info("Saved investor with portfolioId={}", newId);

            return new PortfolioResponse(newId);

        } catch (DataIntegrityViolationException e) {
            log.error("Unique constraint violation for phoneNumber={}", req.getPhoneNumber(), e);
            throw new PortfolioCreationException(
                "Phone number already exists", e
            );

        } catch (Exception e) {
            log.error("Failed to create portfolio", e);
            throw new PortfolioCreationException(
                "Failed to create portfolio", e
            );
        }
    }
}
