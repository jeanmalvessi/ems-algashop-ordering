package com.algaworks.algashop.ordering.infrastructure.adapters.input.listener.customer;

import com.algaworks.algashop.ordering.core.domain.model.customer.CustomerArchivedEvent;
import com.algaworks.algashop.ordering.core.domain.model.customer.CustomerRegisteredEvent;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderReadyEvent;
import com.algaworks.algashop.ordering.core.ports.input.customer.ForAddingCustomerLoyaltyPoints;
import com.algaworks.algashop.ordering.core.ports.input.customer.ForConfirmCustomerRegistration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerEventListener {

    private final ForConfirmCustomerRegistration forConfirmCustomerRegistration;
    private final ForAddingCustomerLoyaltyPoints forAddingCustomerLoyaltyPoints;

    @EventListener
    public void listen(CustomerRegisteredEvent event) {
        forConfirmCustomerRegistration.confirm(event.customerId().value());
    }

    @EventListener
    public void listen(CustomerArchivedEvent event) {
        log.info("CustomerArchivedEvent listener");
    }

    @EventListener
    public void listen(OrderReadyEvent event) {
        forAddingCustomerLoyaltyPoints.addLoyaltyPoints(event.customerId().value(), event.orderId().toString());
    }
}
