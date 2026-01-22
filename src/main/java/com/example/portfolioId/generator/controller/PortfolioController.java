package com.example.portfolioId.generator.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.portfolioId.generator.dto.PortfolioDetailsResponse;
import com.example.portfolioId.generator.dto.PortfolioRequest;
import com.example.portfolioId.generator.dto.PortfolioResponse;
import com.example.portfolioId.generator.service.PortfolioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
@Slf4j
public class PortfolioController {

    private final PortfolioService service;

    @PostMapping("/create")
    public PortfolioResponse createPortfolio(@RequestBody PortfolioRequest req) {

        log.info("Controller received: name={}, phoneNumber={}, address={}",
                req.getName(), req.getPhoneNumber(), req.getAddress());

        return service.createPortfolio(req);
    }

    @GetMapping("/{id}")
    public PortfolioDetailsResponse getPortfolioById(@PathVariable UUID id) {

        return service.getPortfolioById(id);
    }

    @GetMapping("/all")
    public List<PortfolioDetailsResponse> getAllPortfolios() {

        return service.getAllPortfolios();
    }

}
