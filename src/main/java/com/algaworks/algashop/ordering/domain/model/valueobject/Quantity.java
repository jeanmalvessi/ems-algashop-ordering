package com.algaworks.algashop.ordering.domain.model.valueobject;

import com.algaworks.algashop.ordering.domain.model.exception.ErrorMessages;

import java.io.Serializable;
import java.util.Objects;

public record Quantity(Integer value) implements Serializable, Comparable<Quantity> {

    public static final Quantity ZERO = new Quantity(0);

    public Quantity {
        Objects.requireNonNull(value);
        if (value < 0) {
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_QUANTITY_IS_LESS_THAN_ZERO);
        }
    }

    public Quantity add(Quantity quantity) {
        Objects.requireNonNull(quantity);
        return new Quantity(this.value + quantity.value());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(Quantity o) {
        return this.value.compareTo(o.value);
    }
}
