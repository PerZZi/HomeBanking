package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.models.Transaction;

public interface TransactionService {

    void saveTransaction(Transaction transaction);
}
