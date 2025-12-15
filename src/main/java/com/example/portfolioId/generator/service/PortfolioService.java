package com.example.portfolioId.generator.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.portfolioId.generator.dto.PortfolioRequest;
import com.example.portfolioId.generator.dto.PortfolioResponse;
import com.example.portfolioId.generator.entity.InvestorDetails;
import com.example.portfolioId.generator.exception.PortfolioCreationException;
import com.example.portfolioId.generator.repository.InvestorDetailsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioService {

    private final InvestorDetailsRepository investorRepo;

    public PortfolioResponse createPortfolio(PortfolioRequest req) {

        log.info("Service received phoneNumber={}", req.getPhoneNumber());

        try {
            UUID newId = UUID.randomUUID();

            InvestorDetails investor = new InvestorDetails();
            investor.setPortfolioId(newId);
            investor.setName(req.getName());
            investor.setPhoneNumber(req.getPhoneNumber());
            investor.setAddress(req.getAddress());

            investorRepo.save(investor);

            log.info("Saved investor with portfolioId={}", newId);

            return new PortfolioResponse(newId);

        } catch (Exception e) {
            log.error("Failed to create portfolio", e);
            throw new PortfolioCreationException("Failed to create portfolio", e);
        }
    }
}
