package com.MindHub.homebanking.services.implement;

import com.MindHub.homebanking.dtos.AccountDTO;
import com.MindHub.homebanking.models.Account;
import com.MindHub.homebanking.repositories.AccountRepository;
import com.MindHub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public List<AccountDTO> getAccount() {
        return accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(toList());
    }

    @Override
    public AccountDTO getAccount(Long id) {
        return accountRepository.findById(id).map(account -> new AccountDTO(account)).orElse(null);
    }

    @Override
    public Account getAccountNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findByNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber);
    }
}
