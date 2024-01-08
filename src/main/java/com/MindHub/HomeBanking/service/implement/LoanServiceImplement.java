package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.dto.LoanDTO;
import com.MindHub.HomeBanking.models.Loan;
import com.MindHub.HomeBanking.repositories.LoanRepositories;
import com.MindHub.HomeBanking.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    private LoanRepositories loanRepositories;


    @Override
    public List<LoanDTO> getAllLoans() {
        return getLoan().stream().map(loan -> new LoanDTO(loan)).collect(Collectors.toList()) ;
    }

    @Override
    public List<Loan> getLoan() {
        return loanRepositories.findAll();
    }

    @Override
    public Loan findById(Long id) {
        return loanRepositories.findById(id).orElse(null);
    }

    @Override
    public LoanDTO findByIdLoanDTO(Long id) {
        return loanRepositories.findById(id).map(loan -> new LoanDTO(loan)).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return loanRepositories.existsById(id);
    }
}
