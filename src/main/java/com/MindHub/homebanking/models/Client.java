package com.MindHub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName, lastName,email,password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set <Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoan = new HashSet<>();

    @OneToMany(mappedBy = "holder", fetch = FetchType.EAGER)
    private Set<Card> card = new HashSet<>();

    public Client(){}

    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public long getId() {return id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String toString() {return firstName + " " + lastName;}

    public String getEmail() {return email;}
    public void setEmail(String emailClient) {this.email = emailClient;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public Set<Account> getAccounts() {return accounts;}

    public Set<ClientLoan> getClientLoan() {return clientLoan;}

    public Set<Card> getCards() {return card;}
}