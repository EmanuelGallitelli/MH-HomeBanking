package com.MindHub.homebanking.dtos;

import com.MindHub.homebanking.models.Transaction;
import com.MindHub.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {
    private long id;

    private TransactionType type;
    private String description;
    private double amount;
    private LocalDateTime date;

    public TransactionDTO(){}

    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.description = transaction.getDescription();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
    }

    public TransactionType getType() {return type;}
    public void setType(TransactionType type) {this.type = type;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}

    public LocalDateTime getDate() {return date;}
    public void setDate(LocalDateTime date) {this.date = date;}
}
