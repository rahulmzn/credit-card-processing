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
 */

@Document(collection = "cards")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Card extends BaseDocument<String> {

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("number")
    @NotNull
    @CardNumber
    private String number;

    @JsonProperty("limit")
    @NotNull
    private String limit;

    @JsonProperty("balance")
    private double balance ;

    @JsonProperty("brand")
    @NotNull
    private Brand brand;

}
