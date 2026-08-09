package com.algaworks.algashop.ordering.core.ports.output.order;

import com.algaworks.algashop.ordering.core.ports.input.order.OrderFilter;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ForObtainingOrders {
    OrderDetailOutput findById(String id);
    Page<OrderSummaryOutput> filter(OrderFilter filter);
    OrderDetailOutput findByIdAndCustomerId(String id, UUID customerId);
}
