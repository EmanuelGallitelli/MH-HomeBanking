package com.MindHub.homebanking.controller;

import com.MindHub.homebanking.dtos.TransactionDTO;
import com.MindHub.homebanking.models.Account;
import com.MindHub.homebanking.models.Client;
import com.MindHub.homebanking.models.Transaction;
import com.MindHub.homebanking.services.AccountService;
import com.MindHub.homebanking.services.ClientService;
import com.MindHub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.MindHub.homebanking.models.TransactionType.CREDIT;
import static com.MindHub.homebanking.models.TransactionType.DEBIT;

@RestController
@RequestMapping("/api")

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Transactional
    @GetMapping("/transanction")
    public List<TransactionDTO> getTransaction() {
        return transactionService.getTransaction();
    }

    @GetMapping("/transanction/{id}")
    public TransactionDTO getClient(@PathVariable Long id) {
        return transactionService.getClient(id);
    }

    @PostMapping("/transactions")
    public ResponseEntity<Object> createTransaction(
            @RequestParam double amount, @RequestParam String originAccountNumber,
            @RequestParam String destinationAccountNumber, @RequestParam String description, Authentication authentication) {

        Client client = clientService.getClientCurrent(authentication);

        Account originAccount = accountService.getAccountNumber(originAccountNumber);
        Account destinationAccount = accountService.getAccountNumber(destinationAccountNumber);

        if (amount <= 0 || originAccountNumber.contains(" ") || destinationAccountNumber.contains(" ") || description.isEmpty()) {
            return new ResponseEntity<>("Missing data.", HttpStatus.FORBIDDEN);
        }

        if (originAccountNumber.equals(destinationAccountNumber)) {
            return new ResponseEntity<>("You can't send from the same account.", HttpStatus.FORBIDDEN);
        }

        if (accountService.getAccountNumber(String.valueOf(originAccount)) == null) {
            return new ResponseEntity<>("This account does not exist.", HttpStatus.FORBIDDEN);
        }

        if (!client.getAccounts().contains(originAccount)) {
            return new ResponseEntity<>("The source account does not belong to the client.", HttpStatus.FORBIDDEN);
        }

        if (accountService.getAccountNumber(String.valueOf(destinationAccount)) == null) {
            return new ResponseEntity<>("Destination account does not exist.", HttpStatus.FORBIDDEN);
        }

        if (amount > originAccount.getBalance()) {
            return new ResponseEntity<>("You don't have enough money in your account.", HttpStatus.FORBIDDEN);
        }

        Transaction transactionDebit = new Transaction(originAccount, DEBIT, description, amount, LocalDateTime.now());
        transactionService.saveTransaction(transactionDebit);

        Transaction transactionCredit = new Transaction(destinationAccount, CREDIT, description, amount, LocalDateTime.now());
        transactionService.saveTransaction(transactionCredit);

        originAccount.setBalance(originAccount.getBalance() - amount);
        accountService.saveAccount(originAccount);

        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
        accountService.saveAccount(destinationAccount);

        return new ResponseEntity<>("Transaction made!",HttpStatus.CREATED);
    }
}