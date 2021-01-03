package com.bank.credit.card.api.repository;

import com.bank.credit.card.api.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CreditCardRepository will be handling CURD communication with database requested by service
 */

public interface CreditCardRepository extends MongoRepository<Card, String> {

}
