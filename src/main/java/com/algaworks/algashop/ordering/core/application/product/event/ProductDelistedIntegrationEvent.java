package com.algaworks.algashop.ordering.core.application.product.event;

import com.algaworks.algashop.ordering.core.application.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDelistedIntegrationEvent implements IntegrationEvent {
    private UUID productId;
    private OffsetDateTime delistedAt;

    @Override
    public String getAggregateId() {
        return productId.toString();
    }
}
