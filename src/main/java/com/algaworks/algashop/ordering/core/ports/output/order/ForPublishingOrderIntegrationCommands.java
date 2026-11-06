package com.algaworks.algashop.ordering.core.ports.output.order;

import com.algaworks.algashop.ordering.core.application.IntegrationCommand;

public interface ForPublishingOrderIntegrationCommands {
    void send(IntegrationCommand command);
}
