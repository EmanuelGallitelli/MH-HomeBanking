package com.MindHub.homebanking;

import com.MindHub.homebanking.models.*;
import com.MindHub.homebanking.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

import static com.MindHub.homebanking.models.CardColor.*;
import static com.MindHub.homebanking.models.TransactionType.*;

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {SpringApplication.run(HomebankingApplication.class, args);}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transanctionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return (args) -> {
			LocalDateTime today = LocalDateTime.now();

			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("melba"));
			clientRepository.save(client1);

			Account account1 = new Account(client1, "VIN-001", LocalDateTime.now(), 5000);
			accountRepository.save(account1);
			Account account2 = new Account(client1, "VIN-002", today.plusDays(1), 7500);
			accountRepository.save(account2);

			Transaction transaction1 = new Transaction(account1, DEBIT, "First Transaction", 5451.14, LocalDateTime.now());
			transanctionRepository.save(transaction1);
			Transaction transaction2 = new Transaction(account2, CREDIT, "Second Transaction", 74169.22, LocalDateTime.now());
			transanctionRepository.save(transaction2);
			Transaction transaction3 = new Transaction(account1, DEBIT, "HBO Max", 5451.14, LocalDateTime.now());
			transanctionRepository.save(transaction3);
			Transaction transaction4 = new Transaction(account2, CREDIT, "Second Transaction", 74169.22, LocalDateTime.now());
			transanctionRepository.save(transaction4);
			Transaction transaction5 = new Transaction(account1, DEBIT, "Spotify", 5451.14, LocalDateTime.now());
			transanctionRepository.save(transaction5);
			Transaction transaction6 = new Transaction(account2, CREDIT, "Second Transaction", 74169.22, LocalDateTime.now());
			transanctionRepository.save(transaction6);
			Transaction transaction7 = new Transaction(account1, DEBIT, "YouTube", 5451.14, LocalDateTime.now());
			transanctionRepository.save(transaction7);
			Transaction transaction8 = new Transaction(account2, CREDIT, "Second Transaction", 74169.22, LocalDateTime.now());
			transanctionRepository.save(transaction8);

			Loan Hipotecario = new Loan("Hipotecario", 500000, List.of(12, 24, 48, 60));
			loanRepository.save(Hipotecario);
			Loan Personal = new Loan("Personal", 100000, List.of(6, 12, 24));
			loanRepository.save(Personal);
			Loan Automotriz = new Loan("Automotriz", 300000, List.of(6, 12, 24, 36));
			loanRepository.save(Automotriz);

			ClientLoan clientLoan1 = new ClientLoan(400000, 60, client1, Hipotecario);
			clientLoanRepository.save(clientLoan1);
			ClientLoan clientLoan2 = new ClientLoan(50000, 12, client1, Personal);
			clientLoanRepository.save(clientLoan2);

			Card card1 = new Card(client1, client1.getFirstName() + " " + client1.getLastName(), GOLD, CardType.DEBIT, "4962 8451 2156 1452", "042", today, today.plusYears(5));
			cardRepository.save(card1);
			Card card2 = new Card(client1, client1.getFirstName() + " " + client1.getLastName(), TITANIUM, CardType.CREDIT, "3494 1576 1214 8766", "196", today, today.plusYears(5));
			cardRepository.save(card2);
			Card card4 = new Card(client1, client1.getFirstName() + " " + client1.getLastName(), SILVER, CardType.DEBIT, "4559 0122 6211 5616", "361", today.plusMonths(10), today.plusYears(2));
			cardRepository.save(card4);
			Card card5 = new Card(client1, client1.getFirstName() + " " + client1.getLastName(), GOLD, CardType.CREDIT, "3159 5124 6214 6924", "519", today.plusMonths(7), today.plusYears(3));
			cardRepository.save(card5);
			Card card6 = new Card(client1, client1.getFirstName() + " " + client1.getLastName(), TITANIUM, CardType.DEBIT, "4341 2547 7823 9987", "651", today.plusMonths(6), today.plusYears(6));
			cardRepository.save(card6);
			Card card7 = new Card(client1, client1.getFirstName() + " " + client1.getLastName(), SILVER, CardType.CREDIT, "3346 5247 9034 0314", "036", today.plusMonths(3), today.plusYears(7));
			cardRepository.save(card7);

			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			Client client2 = new Client("Emanuel", "Gallitelli", "emanuelgallitelli@mindhub.com",passwordEncoder.encode("1234"));
			clientRepository.save(client2);

			Account account3 = new Account(client2, "VIN-003", LocalDateTime.now(), 50000);
			accountRepository.save(account3);
			Account account4 = new Account(client2, "VIN-004", today.plusDays(1), 75000);
			accountRepository.save(account4);

			Card card3 = new Card(client2, client2.getFirstName() + " " + client2.getLastName(), SILVER, CardType.CREDIT, "3963 2348 1277 6412", "723", today, today.plusYears(5));
			cardRepository.save(card3);
			Card card8 = new Card(client2, client2.getFirstName() + " " + client2.getLastName(), GOLD, CardType.DEBIT, "4503 0035 8143 7023", "904", today.plusMonths(2), today.plusYears(2));
			cardRepository.save(card8);
			Card card9 = new Card(client2, client2.getFirstName() + " " + client2.getLastName(), TITANIUM, CardType.CREDIT, "3245 3256 4587 3694", "571", today.plusMonths(9), today.plusYears(7));
			cardRepository.save(card9);

			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			Client admin = new Client("admin","admin","admin@adminmh.com",passwordEncoder.encode("1234"));
			clientRepository.save(admin);

			Account account5 = new Account(admin, "VIN-005", today.plusDays(1), 45000);
			accountRepository.save(account5);
			Account account6 = new Account(admin, "VIN-006", today.plusDays(1), 500000);
			accountRepository.save(account6);
		};
	}
}