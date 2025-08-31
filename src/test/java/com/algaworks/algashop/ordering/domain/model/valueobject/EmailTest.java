package com.algaworks.algashop.ordering.domain.model.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateValidEmail() {
        String address = "user@domain.com";
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
            "userdomain.com",
            "@domain.com",
            "user@",
            "user@.com",
            "user@domain.",
            "user.@domain.com",
            "us..er@domain.com",
            "user@dom ain.com"
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
            "user@domain.com",
            "user.name@domain.com",
            "user+tag@domain.com",
            "user@subdomain.domain.com",
            "123@domain.com",
            "user@domain.co.uk"
        };

        for (String validEmail : validEmails) {
            assertDoesNotThrow(() -> new Email(validEmail));
        }
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        String address = "user@domain.com";
        Email email = new Email(address);

        assertEquals(address, email.toString());
    }
}
