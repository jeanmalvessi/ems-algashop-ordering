package com.algaworks.algashop.ordering.core.ports.input.order;

import com.algaworks.algashop.ordering.core.ports.output.order.OrderDetailOutput;
import com.algaworks.algashop.ordering.core.ports.output.order.OrderSummaryOutput;
import org.springframework.data.domain.Page;

public interface ForQueryingOrders {
    OrderDetailOutput findById(String id);
    Page<OrderSummaryOutput> filter(OrderFilter filter);
}
