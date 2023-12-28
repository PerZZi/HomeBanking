package com.MindHub.HomeBanking.repositories;

import com.MindHub.HomeBanking.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CardRepositories extends JpaRepository<Card, Long> {

    boolean existsByNumber (String number);
}
