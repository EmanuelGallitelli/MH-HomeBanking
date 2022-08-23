package com.MindHub.homebanking.dtos;

import com.MindHub.homebanking.models.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanDTO {

    private long id;

    private String name;

    private long maxAmount;

    private List<Integer> payments = new ArrayList<>();

    public LoanDTO(){}

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
    }

    public long getId() {return id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public long getMaxAmount() {return maxAmount;}
    public void setMaxAmount(long maxAmount) {this.maxAmount = maxAmount;}

    public List<Integer> getPayments() {return payments;}
    public void setPayments(List<Integer> payments) {this.payments = payments;}
}
