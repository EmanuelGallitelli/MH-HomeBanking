package com.MindHub.homebanking.services.implement;

import com.MindHub.homebanking.dtos.TransactionDTO;
import com.MindHub.homebanking.models.Transaction;
import com.MindHub.homebanking.repositories.TransactionRepository;
import com.MindHub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private  TransactionRepository transactionRepository;

    @Override
    public List<TransactionDTO> getTransaction() {
        return transactionRepository.findAll().stream().map(transanction -> new TransactionDTO(transanction)).collect(toList());
    }

    @Override
    public TransactionDTO getClient(long id) {
        return null;
    }

    @Override
    public void saveTransaction(Transaction transaction) {

    }
}
