package com.MindHub.HomeBanking.repositories;

import com.MindHub.HomeBanking.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientLoanRepositories extends JpaRepository<ClientLoan, Long> {
}
