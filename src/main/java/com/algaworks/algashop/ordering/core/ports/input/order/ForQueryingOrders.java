package com.algaworks.algashop.ordering.core.ports.input.order;

import com.algaworks.algashop.ordering.core.ports.output.order.OrderDetailOutput;
import com.algaworks.algashop.ordering.core.ports.output.order.OrderSummaryOutput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ForQueryingOrders {
    OrderDetailOutput findById(String id);
    OrderDetailOutput findByIdAndCustomerId(String id, UUID customerId);
    Page<OrderSummaryOutput> filter(OrderFilter filter);
}
