package com.bank.credit.card.api.exceptions;

/**
 * EntityNotFoundException can be used when we have entity level errors.
 */

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6934744629594572993L;

    public EntityNotFoundException(String id) {
        super("could not find entity '" + id + "'.");
    }
}
