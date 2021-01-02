package com.bank.credit.card.api.service;

import com.bank.credit.card.api.model.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CardService {

    Card addCard(Card card);

    Page<Card> findAll(Pageable pageable);

}
