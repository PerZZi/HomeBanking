package com.MindHub.HomeBanking.repositories;

import com.MindHub.HomeBanking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepositories extends JpaRepository<Account, Long> {
}
