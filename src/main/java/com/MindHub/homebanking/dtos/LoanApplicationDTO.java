package com.MindHub.homebanking.dtos;

public class LoanApplicationDTO {

    private long id;

    private double amount;

    private int payments;

    private String destinationAccountNumber;

    public LoanApplicationDTO(){}

    public LoanApplicationDTO(long id, double amount, int payments, String destinationAccountNumber) {
        this.id = id;
        this.amount = amount;
        this.payments = payments;
        this.destinationAccountNumber = destinationAccountNumber;
}

    public long getId() {return id;}

    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}

    public int getPayments() {return payments;}
    public void setPayments(int payments) {this.payments = payments;}

    public String getDestinationAccountNumber() {return destinationAccountNumber;}
    public void setDestinationAccountNumber(String numberAccount) {this.destinationAccountNumber = numberAccount;}
}
