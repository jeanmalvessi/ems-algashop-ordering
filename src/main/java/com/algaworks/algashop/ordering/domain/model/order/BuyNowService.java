package com.algaworks.algashop.ordering.domain.model.order;

import com.algaworks.algashop.ordering.domain.model.DomainService;
import com.algaworks.algashop.ordering.domain.model.commons.Money;
import com.algaworks.algashop.ordering.domain.model.customer.Customer;
import com.algaworks.algashop.ordering.domain.model.product.Product;
import com.algaworks.algashop.ordering.domain.model.commons.Quantity;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class BuyNowService {

    private final CustomerHasFreeShippingSpecification customerHasFreeShippingSpecification;

    public Order buyNow(Product product, Customer customer, Billing billing, Shipping shipping, Quantity quantity, PaymentMethod paymentMethod, CreditCardId creditCardId) {

        product.checkOutOfStock();

        Order order = Order.draft(customer.id());
        order.changeBilling(billing);
        order.changePaymentMethod(paymentMethod, creditCardId);
        order.addItem(product, quantity);

        if (hasFreeShipping(customer)) {
            Shipping freeShipping = shipping.toBuilder().cost(Money.ZERO).build();
            order.changeShipping(freeShipping);
        } else {
            order.changeShipping(shipping);
        }

        order.place();

        return order;
    }

    private boolean hasFreeShipping(Customer customer) {
        return customerHasFreeShippingSpecification.isSatisfiedBy(customer);
    }
}
