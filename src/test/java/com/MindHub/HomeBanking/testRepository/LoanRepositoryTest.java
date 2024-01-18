package com.MindHub.HomeBanking.testRepository;

import com.MindHub.HomeBanking.models.Loan;
import com.MindHub.HomeBanking.repositories.LoanRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoanRepositoryTest {

    @Autowired
    LoanRepositories loanRepositories;

    @Test
    public void existLoans(){
        List<Loan> loans = loanRepositories.findAll();
        assertThat(loans,is(not(empty())));

    }
    @Test
    public void existTypeLoan(){
        List<Loan> loans = loanRepositories.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("Hipotecario"))));

    }
}
