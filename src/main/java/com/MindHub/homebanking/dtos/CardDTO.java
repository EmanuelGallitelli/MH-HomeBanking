package com.MindHub.homebanking.dtos;

import com.MindHub.homebanking.models.Card;
import com.MindHub.homebanking.models.CardColor;
import com.MindHub.homebanking.models.CardType;

import java.time.LocalDateTime;

public class CardDTO {

    private long id;
    private String cardholder, number, cvv;
    private CardColor color;
    private CardType type;
    private LocalDateTime fromDate, thruDate;

    public CardDTO() {}

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardholder = card.getCardholder();
        this.number = card.getNumber();
        this.color = card.getColor();
        this.type = card.getType();
        this.cvv = card.getCvv();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
    }

    public long getId() {return id;}

    public String getCardholder() {return cardholder;}
    public void setCardholder(String cardholder) {this.cardholder = cardholder;}

    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}

    public CardColor getColor() {return color;}
    public void setColor(CardColor color) {this.color = color;}

    public CardType getType() {return type;}
    public void setType(CardType type) {this.type = type;}

    public String getCvv() {return cvv;}
    public void setCvv(String cvv) {this.cvv = cvv;}

    public LocalDateTime getFromDate() {return fromDate;}
    public void setFromDate(LocalDateTime fromDate) {this.fromDate = fromDate;}

    public LocalDateTime getThruDate() {return thruDate;}
    public void setThruDate(LocalDateTime thruDate) {this.thruDate = thruDate;}
}
