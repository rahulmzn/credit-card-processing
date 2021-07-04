package com.bank.credit.card.api.controller;

import com.bank.credit.card.api.data.binding.ErrorResource;
import com.bank.credit.card.api.exceptions.InvalidRequestException;
import com.bank.credit.card.api.model.Card;
import com.bank.credit.card.api.service.CardService;
import com.bank.credit.card.api.util.ResourcePaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/*
 * Credit card controller is responsible to handle all credit card related requests
 */


@RestController
@ExposesResourceFor(Card.class)
@RequestMapping(value = ResourcePaths.Card.V1.ROOT)
public class CreditCardController {

    CardService cardService;

    @Autowired
    CreditCardController(CardService cardService){
        this.cardService= cardService;
    }

    /**
     * Responsible to add new card into system
     *
     * @param card  : Card details which needs to be created into system
     * @param bindingResult : error responses after running validation on request body parameters
     * @return {@code @ResponseEntity<Void>}
     */

    // Operation is responsible to generate swagger documentation
    @Operation(summary = "Add credit card", description = "Add new credit card", tags = {"Credit Cards"})

    // Possible response from API
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request completed successfully"),
            @ApiResponse(responseCode = "201", description = "A new resource has been created successfully"),
            @ApiResponse(responseCode = "204", description = "An update to an existing resource has been applied successfully"),
            @ApiResponse(responseCode = "422", description = "The request was well-formed but was unable to be followed due to semantic errors.", content = @Content(schema = @Schema(implementation = ErrorResource.class)))})
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addCreditCard(@RequestBody @Valid Card card, BindingResult bindingResult) {
        // Return error response if entity validation fails
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid " + card.getClass().getSimpleName(), bindingResult);
        }
        // Process storing credit card information in data base
        cardService.addCard(card);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Responsible to fetch all existing card from relative backend system.
     *
     * @return List<Card> : all existing cards fetched from backend system
     */

    // Produce swagger documenration
    @Operation(summary = "Find existing credit cards", description = "Get list of all existing cards", tags = {"Credit Cards"})

    // Possible API response
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request completed successfully"),
            @ApiResponse(responseCode = "404", description = "The request was malformed. The response body will include an error providing further information", content = @Content)})
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Card>> getAllCreditCards() {
        List<Card> cards = cardService.findAll();
        return ResponseEntity.ok(cards);
    }
}
