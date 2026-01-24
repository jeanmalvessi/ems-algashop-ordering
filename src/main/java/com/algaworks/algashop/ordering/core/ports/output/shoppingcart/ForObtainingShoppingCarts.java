package com.algaworks.algashop.ordering.core.ports.output.shoppingcart;

import com.algaworks.algashop.ordering.core.ports.input.shoppingcart.ShoppingCartOutput;

import java.util.UUID;

public interface ForObtainingShoppingCarts {
    ShoppingCartOutput findById(UUID shoppingCartId);
    ShoppingCartOutput findByCustomerId(UUID customerId);
}
