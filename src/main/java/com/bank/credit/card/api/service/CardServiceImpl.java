package com.bank.credit.card.api.service;

import javax.annotation.Resource;

import com.bank.credit.card.api.model.Card;
import com.bank.credit.card.api.repository.CreditCardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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
