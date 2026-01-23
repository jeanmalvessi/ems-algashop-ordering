package com.algaworks.algashop.ordering.core.domain.model.product;

import com.algaworks.algashop.ordering.core.domain.model.product.ProductName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductNameTest {

    @Test
    void shouldCreateWithValidName() {
        ProductName name = new ProductName("Product Test");
        assertEquals("Product Test", name.value());
    }

    @Test
    void shouldThrowExceptionForNullName() {
        assertThrows(NullPointerException.class, () -> new ProductName(null));
    }

    @Test
    void shouldThrowExceptionForBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new ProductName(""));
        assertThrows(IllegalArgumentException.class, () -> new ProductName("   "));
    }

    @Test
    void shouldConvertToString() {
        ProductName name = new ProductName("Product Test");
        assertEquals("Product Test", name.toString());
    }
}
