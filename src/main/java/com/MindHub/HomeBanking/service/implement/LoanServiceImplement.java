package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.dto.LoanDTO;
import com.MindHub.HomeBanking.models.Loan;
import com.MindHub.HomeBanking.repositories.LoanRepositories;
import com.MindHub.HomeBanking.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    private LoanRepositories loanRepositories;


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
