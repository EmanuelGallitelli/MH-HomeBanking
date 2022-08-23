package com.MindHub.homebanking.controller;

import com.MindHub.homebanking.dtos.AccountDTO;
import com.MindHub.homebanking.models.Account;
import com.MindHub.homebanking.models.Client;
import com.MindHub.homebanking.services.AccountService;
import com.MindHub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")

public class AccountController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccount();
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accountService.getAccount(id);
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication){

        Client client = clientService.getClientCurrent(authentication);

        Set<Account> accounts = client.getAccounts();

        if(accounts.size() > 2 ){
            return new ResponseEntity<>("You can not have more than 3 accounts", HttpStatus.FORBIDDEN);
        }

        Account account = new Account(client,"VIN-" + this.getRandomNumber(150,76348129), LocalDateTime.now(),0);
        accountService.saveAccount(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
