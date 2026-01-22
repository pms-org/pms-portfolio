package com.example.portfolioId.generator.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PortfolioDetailsResponse {

    private UUID portfolioId;
    private String name;
    private Long phoneNumber;
    private String address;
}
