package com.MindHub.HomeBanking.testRepository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import com.MindHub.HomeBanking.utils.Utils;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UtilTest {


    @Test
    public void testAccountNumber(){
        String accountNumber = Utils.numberAccount();
        assertThat(accountNumber,is(not(emptyOrNullString())));
    }

    @Test
    public void testCardNumber(){
        String cardNumber = Utils.getCardNumber();
        assertThat(cardNumber,is(not(emptyOrNullString())));
    }

    @Test
    public void testCardCvv(){
        int cardCvv = Utils.GeneratedCardCvv();
        assertThat(cardCvv,is(not(equalTo(000))));
    }

}
