package com.algaworks.algashop.ordering.infrastructure.adapters.input.web.order;

import com.algaworks.algashop.ordering.core.domain.model.order.OrderId;
import com.algaworks.algashop.ordering.core.application.checkout.BuyNowInputTestDataBuilder;
import com.algaworks.algashop.ordering.core.ports.input.checkout.BuyNowInput;
import com.algaworks.algashop.ordering.core.ports.output.order.OrderDetailOutput;
import com.algaworks.algashop.ordering.infrastructure.adapters.input.web.AbstractWebIT;
import com.algaworks.algashop.ordering.infrastructure.adapters.output.persistence.order.OrderPersistenceEntityRepository;
import com.algaworks.algashop.ordering.utils.AlgaShopResourceUtils;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.UUID;

class OrderControllerIT extends AbstractWebIT {

    @Autowired
    private OrderPersistenceEntityRepository orderRepository;

    private static final UUID validCustomerId = UUID.fromString("6e148bd5-47f6-4022-b9da-07cfaa294f7a");
    private static final UUID validProductId = UUID.fromString("fffe6ec2-7103-48b3-8e4f-3b58e43fb75a");

    @BeforeEach
    void setup() {
        super.beforeEach();
    }

    @BeforeAll
    static void setupBeforeAll() {
        initWireMock();
    }

    @AfterAll
    static void afterAll() {
        stopMock();
    }

    @Test
    void shouldListOrdersWhenAuthenticatedAsAdmin() {
        givenAuthenticatedAdmin()
                .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/api/v1/orders")
            .then()
                .assertThat()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldNotListAdministrativeOrdersWhenAuthenticatedAsCustomer() {
        givenAuthenticated()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void shouldListMyOrdersWhenAuthenticatedAsCustomer() {
        givenAuthenticated()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/customers/me/orders")
                .then()
                .assertThat()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldCreateOrderUsingProduct() {
        String json = AlgaShopResourceUtils.readContent("json/create-order-with-product.json");

        String createdOrderId = givenAuthenticated()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType("application/vnd.order-with-product.v1+json")
                .body(json)
            .when()
                .post("/api/v1/customers/me/orders")
            .then()
                .assertThat()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.not(Matchers.emptyString()),
                        "customer.id", Matchers.is(validCustomerId.toString()))
            .extract()
                .jsonPath().getString("id");

        boolean orderExists = orderRepository.existsById(new OrderId(createdOrderId).value().toLong());
        Assertions.assertThat(orderExists).isTrue();

    }

    @Test
    void shouldCreateOrderUsingProduct_DTO() {
        UUID creditCardId = UUID.randomUUID();
        var buyNowInput = BuyNowInputTestDataBuilder.aBuyNowInput()
                .productId(validProductId)
                .creditCardId(creditCardId)
                .build();
        BuyNowInput input = BuyNowInput.builder()
                .productId(buyNowInput.getProductId())
                .quantity(buyNowInput.getQuantity())
                .paymentMethod(buyNowInput.getPaymentMethod())
                .creditCardId(buyNowInput.getCreditCardId())
                .shipping(buyNowInput.getShipping())
                .billing(buyNowInput.getBilling())
                .build();

        OrderDetailOutput orderDetailOutput = givenAuthenticated()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType("application/vnd.order-with-product.v1+json")
                .body(input)
            .when()
                .post("/api/v1/customers/me/orders")
            .then()
                .assertThat()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.not(Matchers.emptyString()),
                        "customer.id", Matchers.is(validCustomerId.toString()))
            .extract()
                .body().as(OrderDetailOutput.class);

        Assertions.assertThat(orderDetailOutput.getCustomer().getId()).isEqualTo(validCustomerId);
        Assertions.assertThat(orderDetailOutput.getCreditCardId()).isEqualTo(creditCardId);

        boolean orderExists = orderRepository.existsById(new OrderId(orderDetailOutput.getId()).value().toLong());
        Assertions.assertThat(orderExists).isTrue();
    }

    @Test
    void shouldNotCreateOrderUsingProductWhenProductNotExists() {
        String json = AlgaShopResourceUtils.readContent("json/create-order-with-invalid-product.json");

        givenAuthenticated()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType("application/vnd.order-with-product.v1+json")
                .body(json)
            .when()
                .post("/api/v1/customers/me/orders")
            .then()
                .assertThat()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE)
                .statusCode(HttpStatus.UNPROCESSABLE_CONTENT.value());
    }

    @Test
    void shouldNotCreateOrderUsingProductWhenCustomerWasNotFound() {
        String json = AlgaShopResourceUtils.readContent("json/create-order-with-product-and-invalid-customer.json");
        givenAuthenticatedUnknownCustomer()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType("application/vnd.order-with-product.v1+json")
                .body(json)
            .when()
                .post("/api/v1/customers/me/orders")
            .then()
                .assertThat()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE)
                .statusCode(HttpStatus.UNPROCESSABLE_CONTENT.value());
    }

    @Test
    void shouldCreateOrderUsingShoppingCart() {
        String json = AlgaShopResourceUtils.readContent("json/create-order-with-shopping-cart.json");

        OrderDetailOutput orderDetailOutput = givenAuthenticated()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType("application/vnd.order-with-shopping-cart.v1+json")
                .body(json)
            .when()
                .post("/api/v1/customers/me/orders")
            .then()
                .assertThat()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.not(Matchers.emptyString()),
                        "customer.id", Matchers.is(validCustomerId.toString()))
            .extract()
                .body().as(OrderDetailOutput.class);

        Assertions.assertThat(orderDetailOutput.getCustomer().getId()).isEqualTo(validCustomerId);

        boolean orderExists = orderRepository.existsById(new OrderId(orderDetailOutput.getId()).value().toLong());
        Assertions.assertThat(orderExists).isTrue();
    }
}
