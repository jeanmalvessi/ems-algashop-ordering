package com.algaworks.algashop.ordering.domain.model.order;

import com.algaworks.algashop.ordering.domain.model.IdGenerator;
import io.hypersistence.tsid.TSID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemIdTest {

    @Test
    void shouldCreateOrderItemIdWhenValueIsValidTSID() {
        TSID validTsid = IdGenerator.generateTsid();
        assertDoesNotThrow(() -> new OrderItemId(validTsid));
    }

    @Test
    void shouldCreateOrderItemIdWhenValueIsValidString() {
        String validTsid = IdGenerator.generateTsid().toString();
        assertDoesNotThrow(() -> new OrderItemId(validTsid));
    }

    @Test
    void shouldCreateOrderItemIdWhenValueIsValidLong() {
        Long validValue = IdGenerator.generateTsid().toLong();
        assertDoesNotThrow(() -> new OrderItemId(validValue));
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(NullPointerException.class, () -> new OrderItemId((TSID) null));
        assertThrows(IllegalArgumentException.class, () -> new OrderItemId((String) null));
    }

    @Test
    void shouldThrowExceptionWhenStringValueIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItemId("invalid-tsid"));
    }

    @Test
    void shouldImplementEqualsAndHashCodeCorrectly() {
        TSID tsid = IdGenerator.generateTsid();
        OrderItemId orderItemId1 = new OrderItemId(tsid);
        OrderItemId orderItemId2 = new OrderItemId(tsid);
        OrderItemId orderItemId3 = new OrderItemId(IdGenerator.generateTsid());

        assertEquals(orderItemId1, orderItemId2);
        assertNotEquals(orderItemId1, orderItemId3);
        assertEquals(orderItemId1.hashCode(), orderItemId2.hashCode());
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        TSID tsid = IdGenerator.generateTsid();
        OrderItemId orderItemId = new OrderItemId(tsid);
        assertEquals(tsid.toString(), orderItemId.toString());
    }
}
