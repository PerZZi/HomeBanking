package com.MindHub.HomeBanking.testRepository;

import com.MindHub.HomeBanking.models.Card;
import com.MindHub.HomeBanking.models.CardType;
import com.MindHub.HomeBanking.repositories.CardRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CardRepositoryTest {

    @Autowired
    CardRepositories cardRepositories;

    @Test
    public void existCards(){
        List<Card> cards = cardRepositories.findAll();
        assertThat(cards,is(not(empty())));
    }

    @Test
    public void cardTypeExist(){
        List<Card> cards = cardRepositories.findAll();
        assertThat(cards, hasItem(hasProperty("type", is(CardType.DEBIT))));
    }
}
