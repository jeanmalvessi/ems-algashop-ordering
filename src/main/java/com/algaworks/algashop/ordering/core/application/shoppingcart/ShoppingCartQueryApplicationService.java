package com.algaworks.algashop.ordering.core.application.shoppingcart;

import com.algaworks.algashop.ordering.core.ports.input.shoppingcart.ForQueryingShoppingCarts;
import com.algaworks.algashop.ordering.core.ports.input.shoppingcart.ShoppingCartOutput;
import com.algaworks.algashop.ordering.core.ports.output.shoppingcart.ForObtainingShoppingCarts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartQueryApplicationService implements ForQueryingShoppingCarts {

    private final ForObtainingShoppingCarts forObtainingShoppingCarts;

    @Override
    public ShoppingCartOutput findById(UUID shoppingCartId) {
        return forObtainingShoppingCarts.findById(shoppingCartId);
    }

    @Override
    public ShoppingCartOutput findByCustomerId(UUID customerId) {
        return forObtainingShoppingCarts.findByCustomerId(customerId);
    }
}
