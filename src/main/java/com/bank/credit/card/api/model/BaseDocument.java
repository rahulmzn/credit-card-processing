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

    /**
     * Generated id for document
     */
    @Id
    @Generated
    T id;


    /**
     * Here we have implemented hash code manually
     *
     * @return @code{hash code value}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Here we have implemented equals manually
     *
     * @param obj to compare
     * @return {@code true} if objects match {@code false} both are not matched
     */
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

    /**
     * Manually generated to string method
     * @return string of object parameters
     */
    @Override
    public String toString() {
        return "BaseDocument [id=" + id + "]";
    }


}
