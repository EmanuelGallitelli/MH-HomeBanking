package com.MindHub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private TransactionType type;
    private String description;
    private double amount;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    private Account account;

    public Transaction(){}

    public Transaction (Account account, TransactionType type, String description, double amount, LocalDateTime date){
        this.account = account;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public long getId() {return id;}

    public Account getAccount() {return account;}
    public void setAccount(Account account) {this.account = account;}

    public TransactionType getType() {return type;}
    public void setType(TransactionType type) {this.type = type;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}

    public LocalDateTime getDate() {return date;}
    public void setDate(LocalDateTime date) {this.date = date;}
}
