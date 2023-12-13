package com.MindHub.HomeBanking.repositories;

import com.MindHub.HomeBanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoanRepositories extends JpaRepository<Loan, Long> {
}
