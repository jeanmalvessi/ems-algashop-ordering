package com.algaworks.algashop.ordering.domain.model.product;

import com.algaworks.algashop.ordering.domain.model.IdGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {

    @Test
    void shouldCreateProductIdWhenValueIsValidUUID() {
        UUID validUuid = IdGenerator.generateTimeBasedUUID();
        assertDoesNotThrow(() -> new ProductId(validUuid));
    }

    @Test
    void shouldCreateProductIdWithDefaultConstructor() {
        ProductId productId = new ProductId();
        assertNotNull(productId.value());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(NullPointerException.class, () -> new ProductId(null));
    }

    @Test
    void shouldImplementEqualsAndHashCodeCorrectly() {
        UUID uuid = IdGenerator.generateTimeBasedUUID();
        ProductId productId1 = new ProductId(uuid);
        ProductId productId2 = new ProductId(uuid);
        ProductId productId3 = new ProductId(IdGenerator.generateTimeBasedUUID());

        assertEquals(productId1, productId2);
        assertNotEquals(productId1, productId3);
        assertEquals(productId1.hashCode(), productId2.hashCode());
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        UUID uuid = IdGenerator.generateTimeBasedUUID();
        ProductId productId = new ProductId(uuid);
        assertEquals(uuid.toString(), productId.toString());
    }
}
