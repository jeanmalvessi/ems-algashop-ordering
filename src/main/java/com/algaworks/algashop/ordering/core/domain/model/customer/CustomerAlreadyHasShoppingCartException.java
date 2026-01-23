package com.algaworks.algashop.ordering.core.domain.model.customer;

import com.algaworks.algashop.ordering.core.domain.model.DomainException;
import com.algaworks.algashop.ordering.core.domain.model.ErrorMessages;

public class CustomerAlreadyHasShoppingCartException extends DomainException {
    public CustomerAlreadyHasShoppingCartException(CustomerId customerId) {
        super(String.format(ErrorMessages.ERROR_CUSTOMER_ALREADY_HAS_SHOPPING_CART, customerId));
    }
}
