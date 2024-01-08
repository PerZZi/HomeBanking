package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.dto.LoanDTO;
import com.MindHub.HomeBanking.models.Loan;

import java.util.List;

public interface LoanService {

    List<LoanDTO> getAllLoans();
    List<Loan>getLoan();

    Loan findById (Long id);

    LoanDTO findByIdLoanDTO (Long id);

    boolean existsById (Long id);
}
