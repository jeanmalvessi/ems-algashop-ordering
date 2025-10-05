package com.algaworks.algashop.ordering.domain.model.order.shipping;

import com.algaworks.algashop.ordering.domain.model.order.shipping.ShippingCostService.CalculationRequest;
import com.algaworks.algashop.ordering.domain.model.commons.ZipCode;
import com.algaworks.algashop.ordering.domain.model.product.ProductCatalogService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class ShippingCostServiceIT {

    @Autowired
    private ShippingCostService shippingCostService;

    @Autowired
    private OriginAddressService originAddressService;

    @MockitoBean
    private ProductCatalogService productCatalogService;

    @Test
    void shouldCalculate() {
        ZipCode origin = originAddressService.originAddress().zipCode();
        ZipCode destination = new ZipCode("12345");

        var calculate = shippingCostService.calculate(new CalculationRequest(origin, destination));

        Assertions.assertThat(calculate.cost()).isNotNull();
        Assertions.assertThat(calculate.expectedDate()).isNotNull();
    }
}
