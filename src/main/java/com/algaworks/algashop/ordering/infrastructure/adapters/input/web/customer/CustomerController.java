package com.algaworks.algashop.ordering.infrastructure.adapters.input.web.customer;

import com.algaworks.algashop.ordering.core.ports.input.customer.*;
import com.algaworks.algashop.ordering.core.ports.input.shoppingcart.ShoppingCartOutput;
import com.algaworks.algashop.ordering.core.ports.input.shoppingcart.ForQueryingShoppingCarts;
import com.algaworks.algashop.ordering.infrastructure.adapters.input.web.PageModel;
import com.algaworks.algashop.ordering.infrastructure.config.security.SecurityAnnotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ForQueryingCustomers forQueryingCustomers;
    private final ForQueryingShoppingCarts forQueryingShoppingCarts;

    @GetMapping
    @CanReadCustomers
    public PageModel<CustomerSummaryOutput> findAll(CustomerFilter customerFilter) {
        return PageModel.of(forQueryingCustomers.filter(customerFilter));
    }

    @GetMapping("/{customerId}")
    @CanReadCustomers
    public CustomerOutput findById(@PathVariable UUID customerId) {
        return forQueryingCustomers.findById(customerId);
    }

    @GetMapping("/{customerId}/shopping-cart")
    @CanReadShoppingCarts
    public ShoppingCartOutput findShoppingCartByCustomerId(@PathVariable UUID customerId) {
        return forQueryingShoppingCarts.findByCustomerId(customerId);
    }
}
