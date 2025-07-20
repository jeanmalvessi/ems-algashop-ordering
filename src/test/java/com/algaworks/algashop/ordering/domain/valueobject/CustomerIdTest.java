package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIdTest {

    @Test
    void shouldCreateValidCustomerId() {
        UUID id = UUID.randomUUID();
        CustomerId customerId = new CustomerId(id);

        assertNotNull(customerId);
        assertEquals(id, customerId.value());
    }

    @Test
    void shouldCreateNewCustomerId() {
        assertNotNull(new CustomerId().value());
    }

    @Test
    void shouldThrowExceptionForNullId() {
        assertThrows(NullPointerException.class, () -> new CustomerId(null));
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        UUID id = UUID.randomUUID();
        CustomerId customerId = new CustomerId(id);

        assertEquals(id.toString(), customerId.toString());
    }
}
