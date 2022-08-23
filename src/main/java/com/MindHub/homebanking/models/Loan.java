package com.MindHub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    private long maxAmount;

    @ElementCollection
    @Column(name="payments")
    private List<Integer> payments = new ArrayList<>();

    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoan = new HashSet<>();

    public Loan(){}

    public Loan(String name, long maxAmount, List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }

    public long getId() {return id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public long getMaxAmount() {return maxAmount;}
    public void setMaxAmount(long maxAmount) {this.maxAmount = maxAmount;}

    public List<Integer> getPayments() {return payments;}
    public void setPayments(List<Integer> payments) {this.payments = payments;}

    public Set<ClientLoan> getClientLoan() {return clientLoan;}
    public void setClientLoan(Set<ClientLoan> clientLoan) {this.clientLoan = clientLoan;}
}
