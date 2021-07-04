package com.bank.credit.card.api.service;

import com.bank.credit.card.api.builder.CreditCardBuilder;
import com.bank.credit.card.api.model.Brand;
import com.bank.credit.card.api.model.Card;
import com.bank.credit.card.api.repository.CardRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceImplTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private CardRepository cardRepository;

    @Mock
    Pageable pageable;

    @InjectMocks
    CreditCardServiceImpl cardService;

    @Before
    public void init() {
    }

    @Test
    void addCard() {
        Card card = CreditCardBuilder.builder().name("Unit Test").number("4386280033772018").balance(10.0).limit("1000").brand(Brand.VISA).build().buildCard();
        when(cardRepository.save(any())).thenReturn(card);
        cardService.addCard(card);
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    void findAll() {
        when(cardRepository.findAll()).thenReturn(Collections.emptyList());
        cardService.findAll();
        verify(cardRepository, times(1)).findAll(pageable);
    }
}
