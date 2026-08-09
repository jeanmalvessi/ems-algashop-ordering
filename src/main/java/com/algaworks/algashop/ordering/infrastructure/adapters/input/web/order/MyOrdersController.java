package com.algaworks.algashop.ordering.infrastructure.adapters.input.web.order;

import com.algaworks.algashop.ordering.core.application.checkout.BuyNowApplicationService;
import com.algaworks.algashop.ordering.core.application.checkout.CheckoutApplicationService;
import com.algaworks.algashop.ordering.core.application.security.SecurityChecks;
import com.algaworks.algashop.ordering.core.domain.model.customer.CustomerNotFoundException;
import com.algaworks.algashop.ordering.core.domain.model.product.ProductNotFoundException;
import com.algaworks.algashop.ordering.core.domain.model.shoppingcart.ShoppingCartNotFoundException;
import com.algaworks.algashop.ordering.core.ports.input.checkout.BuyNowInput;
import com.algaworks.algashop.ordering.core.ports.input.checkout.CheckoutInput;
import com.algaworks.algashop.ordering.core.ports.input.order.ForQueryingOrders;
import com.algaworks.algashop.ordering.core.ports.input.order.OrderFilter;
import com.algaworks.algashop.ordering.core.ports.output.order.OrderDetailOutput;
import com.algaworks.algashop.ordering.core.ports.output.order.OrderSummaryOutput;
import com.algaworks.algashop.ordering.infrastructure.adapters.input.web.PageModel;
import com.algaworks.algashop.ordering.infrastructure.adapters.input.web.exception.UnprocessableEntityException;
import com.algaworks.algashop.ordering.infrastructure.config.security.SecurityAnnotations.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/customers/me/orders")
@RequiredArgsConstructor
public class MyOrdersController {

    private final ForQueryingOrders orderQueryService;
    private final CheckoutApplicationService checkoutApplicationService;
    private final BuyNowApplicationService buyNowApplicationService;
    private final SecurityChecks securityChecks;

    @CanReadMyOrders
    @GetMapping("/{orderId}")
    public OrderDetailOutput findById(@PathVariable String orderId) {
        return orderQueryService.findByIdAndCustomerId(orderId, securityChecks.getAuthenticatedUserId());
    }

    @CanReadMyOrders
    @GetMapping
    public PageModel<OrderSummaryOutput> filter(OrderFilter filter) {
        filter.setCustomerId(securityChecks.getAuthenticatedUserId());
        return PageModel.of(orderQueryService.filter(filter));
    }

    @CanWriteMyOrders
    @PostMapping(consumes = "application/vnd.order-with-product.v1+json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetailOutput createWithProduct(@Valid @RequestBody BuyNowInput input) {
        input.setCustomerId(securityChecks.getAuthenticatedUserId());

        String orderId;
        try {
            orderId = buyNowApplicationService.buyNow(input);
        } catch (CustomerNotFoundException | ProductNotFoundException e) {
            throw new UnprocessableEntityException(e.getMessage(), e);
        }

        return orderQueryService.findById(orderId);
    }

    @CanWriteMyOrders
    @PostMapping(consumes = "application/vnd.order-with-shopping-cart.v1+json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetailOutput createWithShoppingCart(@Valid @RequestBody CheckoutInput input) {
        input.setCustomerId(securityChecks.getAuthenticatedUserId());

        String orderId;
        try {
            orderId = checkoutApplicationService.checkout(input);
        } catch (CustomerNotFoundException | ShoppingCartNotFoundException e) {
            throw new UnprocessableEntityException(e.getMessage(), e);
        }

        return orderQueryService.findById(orderId);
    }
}
