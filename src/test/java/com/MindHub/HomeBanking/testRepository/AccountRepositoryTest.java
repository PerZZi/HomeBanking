//package com.MindHub.HomeBanking.testRepository;
//import com.MindHub.HomeBanking.models.Account;
//import com.MindHub.HomeBanking.repositories.AccountRepositories;
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
//public class AccountRepositoryTest {
//
//    @Autowired
//    AccountRepositories accountRepositories;
//
//    @Test
//    public void existsAccount(){
//        List<Account> accounts = accountRepositories.findAll();
//        assertThat(accounts, is(not(empty())));
//    }
//
//    @Test
//    public void existsNumberAccount(){
//        List<Account> accounts = accountRepositories.findAll();
//        assertThat(accounts, hasItem(hasProperty("number", is("VIN-00001"))));
//    }
//}
