package com.algaworks.algashop.ordering.contract.base;

import com.algaworks.algashop.ordering.core.application.checkout.BuyNowApplicationService;
import com.algaworks.algashop.ordering.core.application.checkout.CheckoutApplicationService;
import com.algaworks.algashop.ordering.core.application.order.OrderDetailOutputTestDataBuilder;
import com.algaworks.algashop.ordering.core.application.order.OrderSummaryOutputTestDataBuilder;
import com.algaworks.algashop.ordering.core.application.security.SecurityChecks;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderNotFoundException;
import com.algaworks.algashop.ordering.core.ports.input.checkout.BuyNowInput;
import com.algaworks.algashop.ordering.core.ports.input.checkout.CheckoutInput;
import com.algaworks.algashop.ordering.core.ports.input.order.ForQueryingOrders;
import com.algaworks.algashop.ordering.core.ports.input.order.OrderFilter;
import com.algaworks.algashop.ordering.core.ports.input.shoppingcart.ForQueryingShoppingCarts;
import com.algaworks.algashop.ordering.core.ports.input.shoppingcart.ShoppingCartOutput;
import com.algaworks.algashop.ordering.infrastructure.adapters.input.web.order.MyOrdersController;
import com.algaworks.algashop.ordering.infrastructure.adapters.input.web.order.OrderController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@WebMvcTest(controllers = {OrderController.class, MyOrdersController.class})
public class OrderBase {

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private ForQueryingOrders forQueryingOrders;

    @MockitoBean
    private BuyNowApplicationService buyNowApplicationService;

    @MockitoBean
    private CheckoutApplicationService checkoutApplicationService;

    @MockitoBean
    private ForQueryingShoppingCarts forQueryingShoppingCarts;

    @MockitoBean
    private SecurityChecks securityChecks;

    public static final String validOrderId = "01226N0640J7Q";
    public static final String notFoundOrderId = "01226N0693HDH";
    public static final UUID validCustomerId = UUID.fromString("6e148bd5-47f6-4022-b9da-07cfaa294f7a");
    public static final UUID validShoppingCartId = UUID.fromString("4f31582a-66e6-4601-a9d3-ff608c2d4461");

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(
            MockMvcBuilders.webAppContextSetup(context)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build()
        );

        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();

        Mockito.when(buyNowApplicationService.buyNow(Mockito.any(BuyNowInput.class)))
                .thenReturn(validOrderId);

        Mockito.when(checkoutApplicationService.checkout(Mockito.any(CheckoutInput.class)))
                .thenReturn(validOrderId);

        Mockito.when(securityChecks.getAuthenticatedUserId())
                .thenReturn(validCustomerId);

        Mockito.when(forQueryingShoppingCarts.findByCustomerId(validCustomerId))
                .thenReturn(ShoppingCartOutput.builder()
                        .id(validShoppingCartId)
                        .customerId(validCustomerId)
                        .build());

        Mockito.when(forQueryingOrders.findById(validOrderId))
                .thenReturn(OrderDetailOutputTestDataBuilder.placedOrder(validOrderId).build());

        Mockito.when(forQueryingOrders.findByIdAndCustomerId(validOrderId, validCustomerId))
                .thenReturn(OrderDetailOutputTestDataBuilder.placedOrder(validOrderId).build());

        Mockito.when(forQueryingOrders.findById(notFoundOrderId))
                .thenThrow(new OrderNotFoundException());

        Mockito.when(forQueryingOrders.filter(Mockito.any(OrderFilter.class)))
                .thenReturn(new PageImpl<>(
                        List.of(OrderSummaryOutputTestDataBuilder.placedOrder().id(validOrderId).build())
                ));
    }
}
