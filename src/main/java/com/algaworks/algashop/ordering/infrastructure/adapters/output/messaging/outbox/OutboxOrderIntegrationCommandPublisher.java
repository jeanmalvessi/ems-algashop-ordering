package com.algaworks.algashop.ordering.infrastructure.adapters.output.messaging.outbox;

import com.algaworks.algashop.ordering.core.application.IntegrationCommand;
import com.algaworks.algashop.ordering.core.ports.output.order.ForPublishingOrderIntegrationCommands;
import com.algaworks.algashop.ordering.infrastructure.config.kafka.AlgaShopMessagingKafkaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "algashop.messaging.outbox.enabled", havingValue = "true")
@RequiredArgsConstructor
public class OutboxOrderIntegrationCommandPublisher implements ForPublishingOrderIntegrationCommands {

    private final OutboxRecorder recorder;
    private final AlgaShopMessagingKafkaProperties properties;

    @Override
    public void send(IntegrationCommand command) {
        recorder.record(properties.getOrderCommandTopicName(), command.getAggregateId(), command);
    }
}
