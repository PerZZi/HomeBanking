package com.MindHub.HomeBanking.repositories;

import com.MindHub.HomeBanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface TransactionRepositories extends JpaRepository<Transaction, Long> {
}
