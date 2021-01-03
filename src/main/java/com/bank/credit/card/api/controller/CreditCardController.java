package com.bank.credit.card.api.controller;

import com.bank.credit.card.api.exceptions.InvalidRequestException;
import com.bank.credit.card.api.model.Card;
import com.bank.credit.card.api.service.CardService;
import com.bank.credit.card.api.util.ResourcePaths;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@ExposesResourceFor(Card.class)
@RequestMapping(value = ResourcePaths.Card.V1.ROOT)
public class CreditCardController {

    @Resource
    CardService cardService;

    @ResponseBody
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addCard(@RequestBody @Valid Card card, BindingResult bindingResult) {
        // Return error response if entity validation fails
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid " + card.getClass().getSimpleName(), bindingResult);
        }
        // Process storing credit card information in data base
        cardService.addCard(card);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Card>> query(Pageable pageable) {
        List<Card> cards = cardService.findAll(pageable);
        return ResponseEntity.ok(cards);
    }
}
