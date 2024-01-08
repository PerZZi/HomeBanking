package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.dto.LoanDTO;
import com.MindHub.HomeBanking.models.Loan;

public interface LoanService {

    Loan findById (Long id);

    LoanDTO findByIdLoanDTO (Long id);

    boolean existsById (Long id);
}
