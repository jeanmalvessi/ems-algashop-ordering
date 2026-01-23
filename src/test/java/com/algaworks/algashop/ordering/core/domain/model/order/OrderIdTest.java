package com.algaworks.algashop.ordering.core.domain.model.order;

import com.algaworks.algashop.ordering.core.domain.model.IdGenerator;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderId;
import io.hypersistence.tsid.TSID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderIdTest {

    @Test
    void shouldCreateOrderIdWhenValueIsValidTSID() {
        TSID validTsid = IdGenerator.generateTsid();
        assertDoesNotThrow(() -> new OrderId(validTsid));
    }

    @Test
    void shouldCreateOrderIdWhenValueIsValidString() {
        String validTsid = IdGenerator.generateTsid().toString();
        assertDoesNotThrow(() -> new OrderId(validTsid));
    }

    @Test
    void shouldCreateOrderIdWhenValueIsValidLong() {
        Long validValue = IdGenerator.generateTsid().toLong();
        assertDoesNotThrow(() -> new OrderId(validValue));
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(NullPointerException.class, () -> new OrderId((TSID) null));
        assertThrows(IllegalArgumentException.class, () -> new OrderId((String) null));
    }

    @Test
    void shouldThrowExceptionWhenStringValueIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new OrderId("invalid-tsid"));
    }

    @Test
    void shouldImplementEqualsAndHashCodeCorrectly() {
        TSID tsid = IdGenerator.generateTsid();
        OrderId orderId1 = new OrderId(tsid);
        OrderId orderId2 = new OrderId(tsid);
        OrderId orderId3 = new OrderId(IdGenerator.generateTsid());

        assertEquals(orderId1, orderId2);
        assertNotEquals(orderId1, orderId3);
        assertEquals(orderId1.hashCode(), orderId2.hashCode());
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        TSID tsid = IdGenerator.generateTsid();
        OrderId orderId = new OrderId(tsid);
        assertEquals(tsid.toString(), orderId.toString());
    }
}
