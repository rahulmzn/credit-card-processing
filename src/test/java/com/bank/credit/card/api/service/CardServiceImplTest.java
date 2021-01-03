package com.bank.credit.card.api.service;

import com.bank.credit.card.api.builder.CreditCardBuilder;
import com.bank.credit.card.api.constraints.validation.CardCheckBase;
import com.bank.credit.card.api.model.Brand;
import com.bank.credit.card.api.model.Card;
import com.bank.credit.card.api.repository.CreditCardRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.sun.tools.doclint.Entity.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    Pageable pageable;

    @InjectMocks
    CardServiceImpl cardService;

    @Before
    public void init() {
    }

    @Test
    void addCard() {
        Card card = CreditCardBuilder.builder().name("Unit Test").number("4386280033772018").balance(10.0).limit("1000").brand(Brand.VISA).build().buildCard();
        when(creditCardRepository.save(any())).thenReturn(card);
        cardService.addCard(card);
        verify(creditCardRepository, times(1)).save(card);
    }

    @Test
    void findAll() {
        when(creditCardRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        List<Card> cards = cardService.findAll(pageable);
        verify(creditCardRepository, times(1)).findAll(pageable);
    }
}
