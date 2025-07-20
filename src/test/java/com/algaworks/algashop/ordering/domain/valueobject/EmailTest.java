package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateValidEmail() {
        String address = "user@example.com";
        Email email = new Email(address);

        assertNotNull(email);
        assertEquals(address, email.value());
    }

    @Test
    void shouldThrowExceptionForNullAddress() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    void shouldThrowExceptionForBlankAddress() {
        assertThrows(IllegalArgumentException.class, () -> new Email(""));
        assertThrows(IllegalArgumentException.class, () -> new Email("   "));
    }

    @Test
    void shouldThrowExceptionForInvalidEmailFormats() {
        String[] invalidEmails = {
            "userexample.com",
            "@example.com",
            "user@",
            "user@.com",
            "user@example.",
            "user.@example.com",
            "us..er@example.com",
            "user@exam ple.com"
        };

        for (String invalidEmail : invalidEmails) {
            assertThrows(IllegalArgumentException.class,
                () -> new Email(invalidEmail),
                "Should fail for: " + invalidEmail);
        }
    }

    @Test
    void shouldAcceptValidEmailFormats() {
        String[] validEmails = {
            "user@example.com",
            "user.name@example.com",
            "user+tag@example.com",
            "user@subdomain.example.com",
            "123@example.com",
            "user@example.co.uk"
        };

        for (String validEmail : validEmails) {
            assertDoesNotThrow(() -> new Email(validEmail));
        }
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        String address = "user@example.com";
        Email email = new Email(address);

        assertEquals(address, email.toString());
    }
}
