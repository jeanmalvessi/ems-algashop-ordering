package com.algaworks.algashop.ordering.infrastructure.adapters.input.messaging.kafka.product;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@ToString
@Builder
public class ProductDelistedIntegrationEvent {
    private UUID productId;
    private OffsetDateTime delistedAt;
}
