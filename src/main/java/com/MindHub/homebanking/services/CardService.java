package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dtos.CardDTO;
import com.MindHub.homebanking.models.Card;

import java.util.Set;

public interface CardService {

    Set<CardDTO> getCardDTO();

    void saveCard(Card card);
}
