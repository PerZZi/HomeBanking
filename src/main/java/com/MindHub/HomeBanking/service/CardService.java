package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.models.Card;

public interface CardService {

    Card findById (Long id);
    void deleteCard(Card card);
    void saveCard (Card card);
}
