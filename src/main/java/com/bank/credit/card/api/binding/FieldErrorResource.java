package com.bank.credit.card.api.binding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Getter
public class FieldErrorResource extends GlobalErrorResource {

    private final String parameter;

    @Builder
    public FieldErrorResource(String parameter, String code, String message) {
        super(code, message);
        this.parameter = parameter;
    }
}
