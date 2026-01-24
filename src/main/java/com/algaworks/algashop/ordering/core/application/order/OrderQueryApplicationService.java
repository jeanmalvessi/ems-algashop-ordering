package com.algaworks.algashop.ordering.core.application.order;

import com.algaworks.algashop.ordering.core.ports.input.order.ForQueryingOrders;
import com.algaworks.algashop.ordering.core.ports.input.order.OrderFilter;
import com.algaworks.algashop.ordering.core.ports.output.order.ForObtainingOrders;
import com.algaworks.algashop.ordering.core.ports.output.order.OrderDetailOutput;
import com.algaworks.algashop.ordering.core.ports.output.order.OrderSummaryOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderQueryApplicationService implements ForQueryingOrders {

    private final ForObtainingOrders forObtainingOrders;

    public OrderDetailOutput findById(String id) {
        return forObtainingOrders.findById(id);
    }

    public Page<OrderSummaryOutput> filter(OrderFilter filter) {
        return forObtainingOrders.filter(filter);
    }
}
