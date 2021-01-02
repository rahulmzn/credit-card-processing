
package com.bank.credit.card.api.binding;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
// NOSONAR
public class ErrorResource implements Serializable {

    private static final long serialVersionUID = -2415907856747146978L;

    private final String code;
    private String exception;
    private String message;
    private transient List<FieldErrorResource> errors;
    private transient List<GlobalErrorResource> globalErrors;

    private String getErrorNumber() {
        return Long.toHexString(System.currentTimeMillis());
    }

    public ErrorResource(Throwable throwable) {
        this.code = getErrorNumber();
        this.exception = throwable.getClass().getSimpleName();
        this.message = throwable.getMessage();
    }

    public ErrorResource(Throwable throwable, List<FieldErrorResource> errors) {
        if (throwable == null) {
            this.code = getErrorNumber();
        } else {
            this.code = getErrorNumber();
            this.exception = throwable.getClass().getSimpleName();
            this.message = throwable.getMessage();
        }
        this.errors = errors;
    }

    public ErrorResource(Throwable throwable, List<GlobalErrorResource> globalErrors, List<FieldErrorResource> errors) {
        if (throwable == null) {
            this.code = getErrorNumber();
        } else {
            this.code = getErrorNumber();
            this.exception = throwable.getClass().getSimpleName();
            this.message = throwable.getMessage();
        }
        this.errors = errors;
        this.globalErrors = globalErrors;
    }

    public ErrorResource(String message) {
        this.code = getErrorNumber();
        this.message = message;
    }

}
