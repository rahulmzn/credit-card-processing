package com.bank.credit.card.api.model;

import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Mongo DB base object
 * @param <T> : extended or implemented object.
 */

@Data
public class BaseDocument<T extends Serializable> {

    @Id
    @Generated
    T id;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings(value = "unchecked")
        BaseDocument<T> other = (BaseDocument<T>) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "BaseDocument [id=" + id + "]";
    }


}
