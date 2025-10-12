package com.algaworks.algashop.ordering.domain.model.order;

import com.algaworks.algashop.ordering.domain.model.commons.Money;
import com.algaworks.algashop.ordering.domain.model.customer.Customer;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCart;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCartItem;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCartCantProceedToCheckoutException;
import com.algaworks.algashop.ordering.domain.model.DomainService;
import com.algaworks.algashop.ordering.domain.model.product.Product;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CheckoutService {

    private final CustomerHasFreeShippingSpecification hasFreeShippingSpecification;

    public Order checkout(Customer customer, ShoppingCart shoppingCart, Billing billing, Shipping shipping, PaymentMethod paymentMethod) {
        if (shoppingCart.isEmpty()) {
            throw new ShoppingCartCantProceedToCheckoutException();
        }
        if (shoppingCart.containsUnavailableItems()) {
            throw new ShoppingCartCantProceedToCheckoutException();
        }

        Order order = Order.draft(shoppingCart.customerId());
        order.changeBilling(billing);

        if (hasFreeShipping(customer)) {
            Shipping freeShipping = shipping.toBuilder().cost(Money.ZERO).build();
            order.changeShipping(freeShipping);
        } else {
            order.changeShipping(shipping);
        }

        order.changePaymentMethod(paymentMethod);

        for (ShoppingCartItem item : shoppingCart.items()) {
            order.addItem(new Product(item.productId(), item.name(), item.price(), item.isAvailable()), item.quantity());
        }

        order.place();
        shoppingCart.empty();

        return order;
    }

    private boolean hasFreeShipping(Customer customer) {
        return hasFreeShippingSpecification.isSatisfiedBy(customer);
    }
}
