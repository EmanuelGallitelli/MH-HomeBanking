package com.MindHub.homebanking.services.implement;

import com.MindHub.homebanking.dtos.CardDTO;
import com.MindHub.homebanking.models.Card;
import com.MindHub.homebanking.repositories.CardRepository;
import com.MindHub.homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Set<CardDTO> getCardDTO() {
        return cardRepository.findAll().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }
}
