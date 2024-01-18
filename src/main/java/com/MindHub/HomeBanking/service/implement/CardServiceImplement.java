package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.models.Card;
import com.MindHub.HomeBanking.repositories.CardRepositories;
import com.MindHub.HomeBanking.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImplement implements CardService {

    @Autowired
    CardRepositories cardRepositories;
    @Override
    public Card findById(Long id) {
        return cardRepositories.findById(id).orElse(null);
    }

    @Override
    public void deleteCard(Card card) {
        Card cards = cardRepositories.findById(card.getId()).orElse(null);
        cards.setState(false);
        cardRepositories.save(cards);
    }

    @Override
    public void saveCard(Card card) {
        cardRepositories.save(card);
    }
}
