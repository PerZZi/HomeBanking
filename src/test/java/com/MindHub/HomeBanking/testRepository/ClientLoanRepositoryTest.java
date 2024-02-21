//package com.MindHub.HomeBanking.testRepository;
//
//import com.MindHub.HomeBanking.models.ClientLoan;
//import com.MindHub.HomeBanking.repositories.ClientLoanRepositories;
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
//public class ClientLoanRepositoryTest {
//
//    @Autowired
//    ClientLoanRepositories clientLoanRepositories;
//
//    @Test
//    public void existsClientLoanList(){
//        List<ClientLoan> clientLoans = clientLoanRepositories.findAll();
//        assertThat(clientLoans, is(not(empty())));
//    }
//
//    /*@Test
//    public void transactionType(){
//        List<ClientLoan> clientLoans = clientLoanRepositories.findAll();
//        assertThat(hasItem(hasProperty()));
//    }*/
//}
