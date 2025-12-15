package com.example.portfolioId.generator.exception;

public class PortfolioCreationException extends RuntimeException {

    public PortfolioCreationException(String message) {
        super(message);
    }

    public PortfolioCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
