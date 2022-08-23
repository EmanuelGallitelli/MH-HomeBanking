package com.MindHub.homebanking.dtos;
import com.MindHub.homebanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private long id;
    private String firstName,lastName,email;
    private Set <AccountDTO> accounts = new HashSet<>();
    private Set <ClientLoanDTO> loans = new HashSet<>();
    private Set <CardDTO> cards = new HashSet<>();

    public ClientDTO(){};

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
        this.loans = client.getClientLoan().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
        this.cards = client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    public long getId() {return id;} //Tipo encapsulamiento: permite ver o no las propiedades (json o pantalla) - retorna - nombre - (parametros){cuerpo}*/
                //get y set: metodos accesores
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public Set<AccountDTO> getAccounts() {return accounts;}

    public Set<ClientLoanDTO> getLoans() {return loans;}

    public Set<CardDTO> getCards() {return cards;}
}