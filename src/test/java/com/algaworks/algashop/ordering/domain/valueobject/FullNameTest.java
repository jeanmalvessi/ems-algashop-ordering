package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FullNameTest {

    @Test
    void shouldCreateValidFullName() {
        String firstName = "João";
        String lastName = "Silva";
        FullName fullName = new FullName(firstName, lastName);

        assertNotNull(fullName);
        assertEquals(firstName, fullName.firstName());
        assertEquals(lastName, fullName.lastName());
    }

    @Test
    void shouldThrowExceptionForNullNames() {
        assertThrows(NullPointerException.class, () -> new FullName(null, "Silva"));
        assertThrows(NullPointerException.class, () -> new FullName("João", null));
    }

    @Test
    void shouldThrowExceptionForBlankNames() {
        assertThrows(IllegalArgumentException.class, () -> new FullName("", "Silva"));
        assertThrows(IllegalArgumentException.class, () -> new FullName("João", ""));
        assertThrows(IllegalArgumentException.class, () -> new FullName("   ", "Silva"));
        assertThrows(IllegalArgumentException.class, () -> new FullName("João", "   "));
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        FullName fullName = new FullName("João", "Silva");
        assertEquals("João Silva", fullName.toString());
    }
}
