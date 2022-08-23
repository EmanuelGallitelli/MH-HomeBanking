package com.MindHub.homebanking.dtos;

import com.MindHub.homebanking.models.ClientLoan;

public class ClientLoanDTO {

    private long id,loanId;

    private double amount;
    private int payments;
    private String name;

    public ClientLoanDTO(){}

   public ClientLoanDTO(ClientLoan clientLoan){
        this.id = clientLoan.getId();
        this.loanId = clientLoan.getLoan().getId();

        this.payments = clientLoan.getPayments();
        this.name = clientLoan.getLoan().getName();
   }

    public long getId() {return id;}

    public long getLoanId() {return loanId;}

    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}

    public int getPayments() {return payments;}
    public void setPayments(int payments) {this.payments = payments;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}