package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.models.Transaction;
import com.MindHub.HomeBanking.repositories.TransactionRepositories;
import com.MindHub.HomeBanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImplement implements TransactionService {

    @Autowired
    private TransactionRepositories transactionRepositories;

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepositories.save(transaction);
    }
}
