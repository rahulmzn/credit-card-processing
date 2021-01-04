
package com.bank.credit.card.api.model;

import com.bank.credit.card.api.constraints.CardNumber;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


/**
 * Common entity object, can be use to store the cards information in database.
 * The reason for giving name as card is to keep
 * this property as generic and later it can be used for other type of  card for e.g. : VISA
 * Data collection will be created in mongo database with below given name
 */

@Document(collection = "cards")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Card extends BaseDocument<String> {

    /**
     * Name of the card holder
     * It must not be empty
     */
    @JsonProperty("name")
    @NotNull
    private String name;

    /**
     *Credit card number
     * It must not be empty
     */
    @JsonProperty("number")
    @NotNull
    @CardNumber
    private String number;

    /**
     * Limit of card
     * It must be empty
     */
    @JsonProperty("limit")
    @NotNull
    private String limit;

    /**
     * Balance of card
     */
    @JsonProperty("balance")
    private double balance ;

    /**
     * Brand of card
     */
    @JsonProperty("brand")
    @NotNull
    private Brand brand;

}
