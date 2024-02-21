//package com.MindHub.HomeBanking.testRepository;
//import com.MindHub.HomeBanking.models.Transaction;
//import com.MindHub.HomeBanking.models.TransactionType;
//import com.MindHub.HomeBanking.repositories.TransactionRepositories;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class TransactionRepositoryTest {
//
//    @Autowired
//    TransactionRepositories transactionRepositories;
//
//    @Test
//    public void existsTransactionsList(){
//        List<Transaction> transactions = transactionRepositories.findAll();
//        assertThat(transactions, is(not(empty())));
//    }
//
//    @Test
//    public void transactionType(){
//        List<Transaction> transactions = transactionRepositories.findAll();
//        assertThat(transactions,hasItem(hasProperty("type", is(TransactionType.CREDIT))));
//    }
//}
