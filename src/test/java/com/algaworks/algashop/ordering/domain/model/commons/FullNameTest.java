package com.algaworks.algashop.ordering.domain.model.commons;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FullNameTest {

    @Test
    void shouldCreateValidFullName() {
        String firstName = "Jean";
        String lastName = "Malvessi";
        FullName fullName = new FullName(firstName, lastName);

        assertNotNull(fullName);
        assertEquals(firstName, fullName.firstName());
        assertEquals(lastName, fullName.lastName());
    }

    @Test
    void shouldThrowExceptionForNullNames() {
        assertThrows(NullPointerException.class, () -> new FullName(null, "Malvessi"));
        assertThrows(NullPointerException.class, () -> new FullName("Jean", null));
    }

    @Test
    void shouldThrowExceptionForBlankNames() {
        assertThrows(IllegalArgumentException.class, () -> new FullName("", "Malvessi"));
        assertThrows(IllegalArgumentException.class, () -> new FullName("Jean", ""));
        assertThrows(IllegalArgumentException.class, () -> new FullName("   ", "Malvessi"));
        assertThrows(IllegalArgumentException.class, () -> new FullName("Jean", "   "));
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        FullName fullName = new FullName("Jean", "Malvessi");
        assertEquals("Jean Malvessi", fullName.toString());
    }
}
