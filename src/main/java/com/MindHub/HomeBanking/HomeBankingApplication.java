package com.MindHub.HomeBanking;

import com.MindHub.HomeBanking.dto.TypeAccount;
import com.MindHub.HomeBanking.models.*;
import com.MindHub.HomeBanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomeBankingApplication {
    @Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepositories clientRepositories, AccountRepositories accountRepositories, TransactionRepositories transactionRepositories, ClientLoanRepositories clientLoanRepositories, LoanRepositories loanRepositories, CardRepositories cardRepositories){
		return args -> {

			Client client1 = new Client("Melba","Morel","melba@mindhub.com", passwordEncoder.encode("melba123"));
			Account cuenta1 =new Account("VIN-00001",LocalDate.now().plusDays(1),5000,true, TypeAccount.CURRENT);
			Account cuenta2 =new Account("VIN-00002",LocalDate.now().plusDays(2),7500,true,TypeAccount.SAVING);
			clientRepositories.save(client1);

			client1.addAccount(cuenta1);
			client1.addAccount(cuenta2);

			accountRepositories.save(cuenta1);
			accountRepositories.save(cuenta2);

			Transaction transaction1 = new Transaction(TransactionType.CREDIT,6000,"Credit", LocalDateTime.now(),0);
			Transaction transaction2 = new Transaction(TransactionType.DEBIT,3000,"Debit", LocalDateTime.now(),0);
			Transaction transaction3 = new Transaction(TransactionType.CREDIT,2000,"Credit", LocalDateTime.now(),0);
			cuenta1.addTransaction(transaction1);
			cuenta1.addTransaction(transaction2);
			cuenta1.addTransaction(transaction3);
			transactionRepositories.save(transaction1);
			transactionRepositories.save(transaction2);
			transactionRepositories.save(transaction3);


			Loan hipotecario = new Loan("Mortgage", 500000,1.2, List.of(12,24,36,48,60));
			Loan personal = new Loan("Personal", 100000,1.15, List.of(6,12,24));
			Loan automotriz = new Loan("Automotive", 300000,1.1, List.of(6,12,24,36));
			loanRepositories.save(hipotecario);
			loanRepositories.save(personal);
			loanRepositories.save(automotriz);


			ClientLoan clientLoan1 = new ClientLoan(400000,60);
			ClientLoan clientLoan2 = new ClientLoan(50000,12);


			client1.addClientLoan(clientLoan1);
			client1.addClientLoan(clientLoan2);

			hipotecario.addClientLoan(clientLoan1);
			personal.addClientLoan(clientLoan2);

			clientLoanRepositories.save(clientLoan1);
			clientLoanRepositories.save(clientLoan2);

			Card card1 = new Card("Melba Morel", CardType.DEBIT, ColorType.GOLD,"3325-6745-7876-4445",990,LocalDate.now(),LocalDate.now().plusYears(6),true);
			Card card2= new Card("Melba Morel", CardType.CREDIT, ColorType.TITANIUM,"2234-6745-552-7888",750,LocalDate.now(),LocalDate.now().plusYears(6),true);
			client1.addCard(card1);
			client1.addCard(card2);
			cardRepositories.save(card1);
			cardRepositories.save(card2);


			System.out.println(client1);
			System.out.println(cuenta1);
			System.out.println(cuenta2);
			System.out.println(transaction1);
			System.out.println(transaction2);
			System.out.println(transaction3);

			Client client2 = new Client("Jorge","Sanchez","jorge@mindHun.com","jorge123");
			Account cuenta3 =new Account("VIN-00003",LocalDate.now().plusDays(3),7700,true,TypeAccount.CURRENT);

			clientRepositories.save(client2);

			client2.addAccount(cuenta3);

			accountRepositories.save(cuenta3);

			System.out.println(client2);
			System.out.println(cuenta3);

			Client admin = new Client("Xavi","Noche","xavi@admin.com",passwordEncoder.encode("admin"));
			admin.setRol(RolType.ADMIN);

			clientRepositories.save(admin);

		};
	}
}
