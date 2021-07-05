package com.bank.credit.card.api.repository;

import com.bank.credit.card.api.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CardRepository will be handling communication to manage card data in mongo database
 */

public interface CardRepository extends MongoRepository<Card, String> {

}
