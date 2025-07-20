package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    void shouldCreateValidDocument() {
        String number = "255-08-0578";
        Document document = new Document(number);

        assertNotNull(document);
        assertEquals(number, document.value());
    }

    @Test
    void shouldThrowExceptionForNullNumber() {
        assertThrows(NullPointerException.class, () -> new Document(null));
    }

    @Test
    void shouldThrowExceptionForBlankNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Document(""));
        assertThrows(IllegalArgumentException.class, () -> new Document("   "));
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        String number = "255-08-0578";
        Document document = new Document(number);

        assertEquals(number, document.toString());
    }
}
