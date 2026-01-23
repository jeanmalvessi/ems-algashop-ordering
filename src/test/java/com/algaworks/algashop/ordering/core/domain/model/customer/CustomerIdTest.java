package com.algaworks.algashop.ordering.core.domain.model.customer;

import com.algaworks.algashop.ordering.core.domain.model.IdGenerator;
import com.algaworks.algashop.ordering.core.domain.model.customer.CustomerId;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIdTest {

    @Test
    void shouldCreateValidCustomerId() {
        UUID id = IdGenerator.generateTimeBasedUUID();
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
        UUID id = IdGenerator.generateTimeBasedUUID();
        CustomerId customerId = new CustomerId(id);

        assertEquals(id.toString(), customerId.toString());
    }
}
