package com.algaworks.algashop.ordering.infrastructure.adapters.input.web.order;

import com.algaworks.algashop.ordering.core.ports.input.order.ForQueryingOrders;
import com.algaworks.algashop.ordering.core.ports.input.order.OrderFilter;
import com.algaworks.algashop.ordering.core.ports.output.order.OrderDetailOutput;
import com.algaworks.algashop.ordering.core.ports.output.order.OrderSummaryOutput;
import com.algaworks.algashop.ordering.infrastructure.adapters.input.web.PageModel;
import com.algaworks.algashop.ordering.infrastructure.config.security.SecurityAnnotations.CanReadOrders;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ForQueryingOrders forQueryingOrders;

    @GetMapping("/{orderId}")
    @CanReadOrders
    public OrderDetailOutput findById(@PathVariable String orderId) {
        return forQueryingOrders.findById(orderId);
    }

    @GetMapping
    @CanReadOrders
    public PageModel<OrderSummaryOutput> filter(OrderFilter filter) {
        return PageModel.of(forQueryingOrders.filter(filter));
    }
}
