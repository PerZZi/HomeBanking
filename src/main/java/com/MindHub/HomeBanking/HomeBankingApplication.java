package com.MindHub.HomeBanking;

import com.MindHub.HomeBanking.models.Cliente;
import com.MindHub.HomeBanking.repositories.ClienteRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClienteRepositories clienteRepositories){
		return args -> {
			Cliente cliente1 = new Cliente("Jorge","Sanchez","jorge@mindHun.com");
			clienteRepositories.save(cliente1);
			System.out.println(cliente1);
		};
	}
}
