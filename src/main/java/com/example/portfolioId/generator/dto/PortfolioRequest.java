package com.example.portfolioId.generator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PortfolioRequest {
 
    private String name;
    @JsonProperty("phoneNumber")
    private Long phoneNumber;
    private String address;
}


