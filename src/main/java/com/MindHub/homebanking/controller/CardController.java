package com.MindHub.homebanking.controller;

import com.MindHub.homebanking.dtos.CardDTO;
import com.MindHub.homebanking.models.Card;
import com.MindHub.homebanking.models.CardColor;
import com.MindHub.homebanking.models.CardType;
import com.MindHub.homebanking.models.Client;

import com.MindHub.homebanking.services.CardService;
import com.MindHub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/cards")
    public Set<CardDTO> getCardDTO() {

        return cardService.getCardDTO();
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam CardType type, @RequestParam CardColor color,
                                             Authentication authentication) {

        Client client = clientService.getClientCurrent(authentication);

        List<Card> filterCard = client.getCards().stream().filter(card -> card.getType() == type).collect(Collectors.toList());

        LocalDateTime today = LocalDateTime.now();

        if(filterCard.size() > 2){
            return new ResponseEntity<>("You can not have more than 3 cards", HttpStatus.FORBIDDEN);
        }

        Card card = new Card(client, client.getFirstName() + " " + client.getLastName(), color, type,
                this.getRandomNumber(4000, 4999) + " " + this.getRandomNumber(1000, 9999) + " " + this.getRandomNumber(1000, 9999) + " " + this.getRandomNumber(1000, 9999),
                this.getRandomNumber(100, 999) + "", today, today.plusYears(5));
        cardService.saveCard(card);

        return new ResponseEntity<>("The card was created successfully", HttpStatus.CREATED);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
