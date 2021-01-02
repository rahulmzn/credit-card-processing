package com.bank.credit.card.api.model;

import com.bank.credit.card.api.constraints.CardNumber;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

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
