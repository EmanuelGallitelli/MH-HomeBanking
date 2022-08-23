package com.MindHub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String cardholder, number, cvv;
    private CardColor color;
    private CardType type;
    private LocalDateTime fromDate, thruDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "holder_id")
    private Client holder;

    public Card() {}

    public Card(Client client, String cardholder, CardColor color, CardType type, String number, String cvv, LocalDateTime fromDate, LocalDateTime thruDate ) {
        this.holder = client;
        this.cardholder = cardholder;
        this.color = color;
        this.type = type;
        this.number = number;
        this.cvv = cvv;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
    }

    public long getId() {return id;}

    public Client getHolder() {return holder;}
    public void setHolder(Client holder) {this.holder = holder;}

    public String getCardholder() {return cardholder;}
    public void setCardholder(String cardholder) {this.cardholder = cardholder;}

    public CardColor getColor() {return color;}
    public void setColor(CardColor color) {this.color = color;}

    public CardType getType() {return type;}
    public void setType(CardType type) {this.type = type;}

    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}

    public String getCvv() {return cvv;}
    public void setCvv(String cvv) {this.cvv = cvv;}

    public LocalDateTime getFromDate() {return fromDate;}
    public void setFromDate(LocalDateTime fromDate) {this.fromDate = fromDate;}

    public LocalDateTime getThruDate() {return thruDate;}
    public void setThruDate(LocalDateTime thruDate) {this.thruDate = thruDate;}
}
