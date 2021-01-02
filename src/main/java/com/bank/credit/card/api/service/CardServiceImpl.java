package com.bank.credit.card.api.service;

import javax.annotation.Resource;

import com.bank.credit.card.api.model.Card;
import com.bank.credit.card.api.repository.CreditCardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CardServiceImpl implements CardService {

    @Resource
    CreditCardRepository creditCardRepository;

    @Override
    public Card addCard(Card card) {
        return creditCardRepository.save(card);
    }

    @Override
    public Page<Card> findAll(Pageable pageable) {
        return creditCardRepository.findAll(pageable);
    }
}
