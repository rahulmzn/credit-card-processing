package com.bank.credit.card.api.service;

import com.bank.credit.card.api.model.Card;

import java.util.List;


public interface CardService {

    Card addCard(Card card);

    List<Card> findAll();

}
