package com.algaworks.algashop.ordering.core.ports.input.checkout;

import com.algaworks.algashop.ordering.core.application.checkout.command.ProcessAcceptedCheckoutIntegrationCommand;

public interface ForProcessingCheckoutAccepted {
    void process(ProcessAcceptedCheckoutIntegrationCommand command);
}
