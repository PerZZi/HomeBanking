package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.models.ClientLoan;
import com.MindHub.HomeBanking.repositories.ClientLoanRepositories;
import com.MindHub.HomeBanking.service.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanServiceImplement implements ClientLoanService {

    @Autowired
    private ClientLoanRepositories clientLoanRepositories;

    @Override
    public void saveClientLoan(ClientLoan clientLoan) {
        clientLoanRepositories.save(clientLoan);
    }
}
