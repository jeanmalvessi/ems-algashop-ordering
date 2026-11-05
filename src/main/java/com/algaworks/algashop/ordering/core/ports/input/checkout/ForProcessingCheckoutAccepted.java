package com.algaworks.algashop.ordering.core.ports.input.checkout;

import com.algaworks.algashop.ordering.core.application.order.event.CheckoutAcceptedIntegrationEvent;

public interface ForProcessingCheckoutAccepted {
    void process(CheckoutAcceptedIntegrationEvent event);
}
