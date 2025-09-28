package com.algaworks.algashop.ordering.domain.model.commons;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void shouldCreateWithValidValue() {
        Quantity quantity = new Quantity(10);
        assertEquals(10, quantity.value());
    }

    @Test
    void shouldCreateZeroQuantity() {
        assertEquals(0, Quantity.ZERO.value());
    }

    @Test
    void shouldThrowExceptionForNullValue() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    void shouldThrowExceptionForNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }

    @Test
    void shouldAddQuantities() {
        Quantity q1 = new Quantity(2);
        Quantity q2 = new Quantity(3);
        assertEquals(5, q1.add(q2).value());
    }

    @Test
    void shouldThrowExceptionWhenAddingNullQuantity() {
        Quantity q1 = new Quantity(2);
        assertThrows(NullPointerException.class, () -> q1.add(null));
    }

    @Test
    void shouldCompareQuantities() {
        Quantity lesser = new Quantity(1);
        Quantity greater = new Quantity(2);
        Quantity equal1 = new Quantity(2);
        Quantity equal2 = new Quantity(2);

        assertTrue(lesser.compareTo(greater) < 0);
        assertTrue(greater.compareTo(lesser) > 0);
        assertEquals(0, equal1.compareTo(equal2));
    }

    @Test
    void shouldConvertToString() {
        Quantity quantity = new Quantity(10);
        assertEquals("10", quantity.toString());
    }
}
