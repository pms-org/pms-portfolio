package com.example.portfolioId.generator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
