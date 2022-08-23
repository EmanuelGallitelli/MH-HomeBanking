package com.MindHub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String  number;
    private LocalDateTime creationDate;
    private double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id")
    private Client owner;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    public Account(){}

    public Account(Client owner, String number, LocalDateTime creationDate, double balance){
    this.owner = owner;
    this.number = number;
    this.creationDate = creationDate;
    this.balance = balance;
    }

    public long getId() {return id;}

    public Client getOwner() {return owner;}
    public void setOwner(Client owner) {this.owner = owner;}

    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}

    public LocalDateTime getCreationDate() {return creationDate;}
    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}

    public Set<Transaction> getTransactions() {return transactions;}
    public void setTransactions(Set<Transaction> transactions) {this.transactions = transactions;}
}
