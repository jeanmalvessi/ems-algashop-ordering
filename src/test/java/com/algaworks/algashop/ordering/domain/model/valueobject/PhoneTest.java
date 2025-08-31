package com.algaworks.algashop.ordering.domain.model.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @Test
    void shouldCreateValidPhone() {
        String number = "11999887766";
        Phone phone = new Phone(number);

        assertNotNull(phone);
        assertEquals(number, phone.value());
    }

    @Test
    void shouldThrowExceptionForNullNumber() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    void shouldThrowExceptionForBlankNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Phone(""));
        assertThrows(IllegalArgumentException.class, () -> new Phone("   "));
    }

    @Test
    void shouldAcceptValidFormats() {
        String[] validNumbers = {
            "11999887766",
            "1155887766"
        };

        for (String validNumber : validNumbers) {
            assertDoesNotThrow(() -> new Phone(validNumber));
        }
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        String number = "11999887766";
        Phone phone = new Phone(number);

        assertEquals(number, phone.toString());
    }
}
