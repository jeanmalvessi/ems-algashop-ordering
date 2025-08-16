package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.valueobject.id.ProductId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ProductTest {

    @Test
    void shouldCreateProductWithValidData() {
        var product = Product.builder()
                .id(new ProductId())
                .name(new ProductName("Test Product"))
                .price(new Money("10.00"))
                .inStock(true)
                .build();

        assertThat(product).isNotNull();
        assertThat(product.id()).isNotNull();
        assertThat(product.name()).isEqualTo(new ProductName("Test Product"));
        assertThat(product.price()).isEqualTo(new Money("10.00"));
        assertThat(product.inStock()).isTrue();
    }

    @Test
    void shouldNotCreateProductWithNullId() {
        assertThatNullPointerException().isThrownBy(() ->
            Product.builder()
                .id(null)
                .name(new ProductName("Test Product"))
                .price(new Money("10.00"))
                .inStock(true)
                .build()
        );
    }

    @Test
    void shouldNotCreateProductWithNullName() {
        assertThatNullPointerException().isThrownBy(() ->
            Product.builder()
                .id(new ProductId())
                .name(null)
                .price(new Money("10.00"))
                .inStock(true)
                .build()
        );
    }

    @Test
    void shouldNotCreateProductWithNullPrice() {
        assertThatNullPointerException().isThrownBy(() ->
            Product.builder()
                .id(new ProductId())
                .name(new ProductName("Test Product"))
                .price(null)
                .inStock(true)
                .build()
        );
    }

    @Test
    void shouldNotCreateProductWithNullInStock() {
        assertThatNullPointerException().isThrownBy(() ->
            Product.builder()
                .id(new ProductId())
                .name(new ProductName("Test Product"))
                .price(new Money("10.00"))
                .inStock(null)
                .build()
        );
    }
}
