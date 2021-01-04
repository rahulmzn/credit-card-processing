package com.bank.credit.card.api.service;

import com.bank.credit.card.api.model.Card;
import com.bank.credit.card.api.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * CardServiceImpl will be responsible to prepare data fetched from database or other backend system.
 */

@Service
public class CardServiceImpl implements CardService {

    @Resource
    CreditCardRepository creditCardRepository;

    @Override
    public Card addCard(Card card) {
        return creditCardRepository.save(card);
    }

    @Override
    public List<Card> findAll() {
        return creditCardRepository.findAll();
    }
}
