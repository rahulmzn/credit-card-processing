package com.bank.credit.card.api.builder;

import com.bank.credit.card.api.model.Brand;
import com.bank.credit.card.api.model.Card;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

import java.util.Objects;

@Builder
@With
public class CreditCardBuilder {

    private final String name;

    private final String number;

    private final String limit;

    private final Double balance;

    private final Brand brand;

    public Card buildCard() {
        if(Objects.nonNull(name) && Objects.nonNull(number) && Objects.nonNull(limit)&& Objects.nonNull(balance)&& Objects.nonNull(brand))
            return new Card(name, number, limit, balance, brand);
        else
            return new Card();
    }

}
