package com.example.portfolioId.generator.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.portfolioId.generator.dto.PortfolioDetailsResponse;
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

        log.info("Saved investor with portfolioId={}", newId);

        return new PortfolioResponse(newId);

    } catch (Exception e) {
        log.error("Failed to create portfolio", e);
        throw new PortfolioCreationException(
                "Failed to create portfolio", e
        );
    }
}


    public PortfolioDetailsResponse getPortfolioById(UUID id) {

        log.info("Fetching portfolio with id={}", id);

        InvestorDetails investor = investorRepo.findById(id)
                .orElseThrow(() -> new PortfolioCreationException(
                "Portfolio not found with id: " + id
        ));

        return new PortfolioDetailsResponse(
                investor.getPortfolioId(),
                investor.getName(),
                investor.getPhoneNumber(),
                investor.getAddress()
        );
    }

    public List<PortfolioDetailsResponse> getAllPortfolios() {

        log.info("Fetching all portfolios");

        return investorRepo.findAll()
                .stream()
                .map(investor -> new PortfolioDetailsResponse(
                investor.getPortfolioId(),
                investor.getName(),
                investor.getPhoneNumber(),
                investor.getAddress()
        ))
                .collect(Collectors.toList());
    }
}
