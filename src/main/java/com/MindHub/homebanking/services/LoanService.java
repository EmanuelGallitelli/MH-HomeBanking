package com.MindHub.homebanking.services;


import com.MindHub.homebanking.dtos.LoanDTO;
import com.MindHub.homebanking.models.ClientLoan;
import com.MindHub.homebanking.models.Loan;

import java.util.List;

public interface LoanService {

    List<LoanDTO> getLoanDTO();

    Loan getLoanFindById (Long id);

    void saveLoan(ClientLoan clientLoan);
}
