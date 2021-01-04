package com.bank.credit.card.api.service;

import com.bank.credit.card.api.model.Card;

import java.util.List;

/**
 * CardService will be responsible to define  information access functions.
 */

public interface CreditCardService {

    Card addCard(Card card);

    List<Card> findAll();

}
