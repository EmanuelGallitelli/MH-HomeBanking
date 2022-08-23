package com.MindHub.homebanking.controller;

import com.MindHub.homebanking.dtos.LoanApplicationDTO;
import com.MindHub.homebanking.dtos.LoanDTO;
import com.MindHub.homebanking.models.*;
import com.MindHub.homebanking.services.AccountService;
import com.MindHub.homebanking.services.ClientService;
import com.MindHub.homebanking.services.LoanService;
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

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionService transactionService;

    @Transactional
    @GetMapping("/loans")
    public List<LoanDTO> getLoanDTO(){
        return loanService.getLoanDTO();
    }

    @PostMapping("/loans")
    public ResponseEntity<Object> createLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){

        Loan loan = loanService.getLoanFindById(loanApplicationDTO.getId());

        Account account = accountService.getAccountNumber(loanApplicationDTO.getDestinationAccountNumber());

        Client clientAuthentication = clientService.getClientCurrent(authentication);


        if(loanApplicationDTO.getAmount() == 0 || loanApplicationDTO.getPayments() == 0){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if(loanService.getLoanFindById(loanApplicationDTO.getId()) == null){
            return new ResponseEntity<>("This loan does not exist", HttpStatus.FORBIDDEN);
        }

        if(loanApplicationDTO.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("You exceeded the maximum amount", HttpStatus.FORBIDDEN);
        }

        if(!loan.getPayments().contains(loanApplicationDTO.getPayments())){
            return new ResponseEntity<>("Payment number not available", HttpStatus.FORBIDDEN);
        }

        if(account == null){
            return new ResponseEntity<>("Destination account does not exist", HttpStatus.FORBIDDEN);
        }

        if(!clientAuthentication.getAccounts().contains(account)){
            return new ResponseEntity<>("destination account does not exist", HttpStatus.FORBIDDEN);
        }

        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount() * 1.20, loanApplicationDTO.getPayments(), clientAuthentication, loan);
        loanService.saveLoan(clientLoan);

        Transaction transaction = new Transaction(account, CREDIT, loan.getName() + " " + "loan approved", loanApplicationDTO.getAmount(), LocalDateTime.now());
        transactionService.saveTransaction(transaction);

        account.setBalance(account.getBalance() + loanApplicationDTO.getAmount());
        accountService.saveAccount(account);

        return new ResponseEntity<>("Transaction done", HttpStatus.CREATED);
    }
}