package com.MindHub.HomeBanking.repositories;

import com.MindHub.HomeBanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClienteRepositories extends JpaRepository<Client, Long> {
}