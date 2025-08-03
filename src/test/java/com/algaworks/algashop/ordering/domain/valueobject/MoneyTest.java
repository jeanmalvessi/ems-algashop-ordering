package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void shouldCreateWithValidStringValue() {
        Money money = new Money("10.50");
        assertEquals(new BigDecimal("10.50"), money.value());
    }

    @Test
    void shouldCreateWithValidBigDecimalValue() {
        Money money = new Money(new BigDecimal("10.50"));
        assertEquals(new BigDecimal("10.50"), money.value());
    }

    @Test
    void shouldCreateZeroMoney() {
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN), Money.ZERO.value());
    }

    @Test
    void shouldRoundToTwoDecimalPlaces() {
        Money money = new Money("10.555");
        assertEquals(new BigDecimal("10.56"), money.value());
    }

    @Test
    void shouldThrowExceptionForNullStringValue() {
        assertThrows(NullPointerException.class, () -> new Money((String) null));
    }

    @Test
    void shouldThrowExceptionForNullBigDecimalValue() {
        assertThrows(NullPointerException.class, () -> new Money((BigDecimal) null));
    }

    @Test
    void shouldThrowExceptionForNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new Money("-1.00"));
        assertThrows(IllegalArgumentException.class, () -> new Money(new BigDecimal("-1.00")));
    }

    @Test
    void shouldMultiplyByQuantity() {
        Money money = new Money("10.00");
        Quantity quantity = new Quantity(2);
        Money result = money.multiply(quantity);
        assertEquals(new BigDecimal("20.00"), result.value());
    }

    @Test
    void shouldThrowExceptionWhenMultiplyingByNullQuantity() {
        Money money = new Money("10.00");
        assertThrows(NullPointerException.class, () -> money.multiply(null));
    }

    @Test
    void shouldThrowExceptionWhenMultiplyingByZeroQuantity() {
        Money money = new Money("10.00");
        Quantity quantity = new Quantity(0);
        assertThrows(IllegalArgumentException.class, () -> money.multiply(quantity));
    }

    @Test
    void shouldAddMoneyValues() {
        Money m1 = new Money("10.00");
        Money m2 = new Money("5.50");
        Money result = m1.add(m2);
        assertEquals(new BigDecimal("15.50"), result.value());
    }

    @Test
    void shouldThrowExceptionWhenAddingNullMoney() {
        Money money = new Money("10.00");
        assertThrows(NullPointerException.class, () -> money.add(null));
    }

    @Test
    void shouldDivideMoney() {
        Money m1 = new Money("10.00");
        Money m2 = new Money("2.00");
        Money result = m1.divide(m2);
        assertEquals(new BigDecimal("5.00"), result.value());
    }

    @Test
    void shouldRoundDivisionResult() {
        Money m1 = new Money("10.00");
        Money m2 = new Money("3.00");
        Money result = m1.divide(m2);
        assertEquals(new BigDecimal("3.33"), result.value());
    }

    @Test
    void shouldCompareMoneyValues() {
        Money lesser = new Money("5.00");
        Money greater = new Money("10.00");
        Money equal1 = new Money("10.00");
        Money equal2 = new Money("10.00");

        assertTrue(lesser.compareTo(greater) < 0);
        assertTrue(greater.compareTo(lesser) > 0);
        assertEquals(0, equal1.compareTo(equal2));
    }

    @Test
    void shouldConvertToString() {
        Money money = new Money("10.50");
        assertEquals("10.50", money.toString());
    }
}
