package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dtos.TransactionDTO;
import com.MindHub.homebanking.models.Transaction;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> getTransaction();

    TransactionDTO getClient(long id);

    void saveTransaction(Transaction transaction);
}
