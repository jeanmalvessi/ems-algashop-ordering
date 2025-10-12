package com.algaworks.algashop.ordering.infrastructure.beans;

import com.algaworks.algashop.ordering.domain.model.order.CustomerHasFreeShippingSpecification;
import com.algaworks.algashop.ordering.domain.model.order.Orders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpecificationBeansConfig {

    @Bean
    public CustomerHasFreeShippingSpecification customerHaveFreeShippingSpecification(Orders orders) {
        return new CustomerHasFreeShippingSpecification(
            orders,
            200,
            2L,
            2000
        );
    }
}
