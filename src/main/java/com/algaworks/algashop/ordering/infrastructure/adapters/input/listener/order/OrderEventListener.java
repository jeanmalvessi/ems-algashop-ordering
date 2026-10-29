package com.algaworks.algashop.ordering.infrastructure.adapters.input.listener.order;

import com.algaworks.algashop.ordering.core.application.order.event.OrderPlacedIntegrationEvent;
import com.algaworks.algashop.ordering.core.application.utility.Mapper;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderCanceledEvent;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderPaidEvent;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderPlacedEvent;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderReadyEvent;
import com.algaworks.algashop.ordering.core.ports.output.order.ForPublishingOrderIntegrationEvents;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener {
    
    private final ForPublishingOrderIntegrationEvents forPublishingOrderIntegrationEvents;
    private final Mapper mapper;

    @EventListener
    public void listen(OrderPlacedEvent event) {
        OrderPlacedIntegrationEvent integrationEvent = mapper.convert(event, OrderPlacedIntegrationEvent.class);
        forPublishingOrderIntegrationEvents.send(integrationEvent);
    }

    @EventListener
    public void listen(OrderPaidEvent event) {
        log.info("OrderPaidEvent listener");
    }

    @EventListener
    public void listen(OrderReadyEvent event) {
        log.info("OrderReadyEvent listener");
    }

    @EventListener
    public void listen(OrderCanceledEvent event) {
        log.info("OrderCanceledEvent listener");
    }
}
