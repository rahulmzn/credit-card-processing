package com.bank.credit.card.api.controller;

import com.bank.credit.card.api.builder.CreditCardBuilder;
import com.bank.credit.card.api.model.Brand;
import com.bank.credit.card.api.model.Card;
import com.bank.credit.card.api.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static com.bank.credit.card.api.util.ResourcePaths.Card.V1.ROOT;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@Slf4j
class CreditCardControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @MockBean
    CardService creditCardService;


    @Autowired
    CreditCardControllerTest(MockMvc mockMvc,ObjectMapper objectMapper){
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void shouldAddCardSuccessfully(){
        Card card = CreditCardBuilder.builder().brand(Brand.VISA).balance(10.0).limit("1000").name("Rahul Kumar").number("4716651077977392").build().buildCard();
        try {
            mockMvc.perform(MockMvcRequestBuilders.post(ROOT)
                    .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(card))
            ).andExpect(status().isCreated());
        } catch (Exception exception) {
           log.error(exception.toString());
        }
    }

    @Test
    public void shouldReturnErrorWhenCardNumberIsNotValid(){
           Card card = CreditCardBuilder.builder().brand(Brand.VISA).balance(10.0).limit("1000").name("Rahul Kumar").number("4716651077").build().buildCard();
            try {
                mockMvc.perform(MockMvcRequestBuilders.post(ROOT)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(card))
                ).andExpect(status().isUnprocessableEntity());
            } catch (Exception exception) {
                log.error(exception.toString());
            }
    }

    @Test
    public void shouldGetAllCardsSuccessfully(){
        try {
            when(creditCardService.findAll()).thenReturn(Arrays.asList(CreditCardBuilder.builder().build().buildCard(),CreditCardBuilder.builder().build().buildCard()));
            mockMvc.perform(MockMvcRequestBuilders.get(ROOT)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk()).andExpect(jsonPath("$",hasSize(2))).andDo(print());
        } catch (Exception exception) {
            log.error(exception.toString());
        }
    }

}

