package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dtos.AccountDTO;
import com.MindHub.homebanking.models.Account;


import java.util.List;

public interface AccountService {

    List<AccountDTO> getAccount();

    AccountDTO getAccount(Long id);

    Account getAccountNumber(String accountNumber);

    void saveAccount(Account account);

    Account findByNumber(String accountNumber);
}
