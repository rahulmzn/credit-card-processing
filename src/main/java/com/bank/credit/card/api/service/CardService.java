package com.bank.credit.card.api.service;

import com.bank.credit.card.api.model.Card;

import java.util.List;

/**
 * Card service will be responsible to define common functionality which can be applied to all type of card services
 */

public interface CardService {

    /**
     * Perform add card operation for given service, this operation can be used to store new card details in backend system
     * @param card : Card object which need to be store in backend system
     * @return : Updated card details with unique id generated after card storedss
     */
    Card addCard(Card card);

    /**
     * Fetch all stored card from backend system without any filter
     * @return : List of all available cards from backend system
     */
    List<Card> findAll();

}
