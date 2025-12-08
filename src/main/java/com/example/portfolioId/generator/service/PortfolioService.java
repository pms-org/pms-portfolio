package  com.example.portfolioId.generator.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.portfolioId.generator.dto.PortfolioRequest;
import com.example.portfolioId.generator.dto.PortfolioResponse;
import com.example.portfolioId.generator.entity.InvestorDetails;
import com.example.portfolioId.generator.repository.InvestorDetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final InvestorDetailsRepository investorRepo;

    public PortfolioResponse createPortfolio(PortfolioRequest req) {

        UUID newId = UUID.randomUUID();

        InvestorDetails investor = new InvestorDetails();
        investor.setPortfolioId(newId);
        investor.setName(req.getName());
        investor.setNumber(req.getNumber());
        investor.setAddress(req.getAddress());

        System.out.print(investor);

        investorRepo.save(investor);

        return new PortfolioResponse(newId);
    }
}
