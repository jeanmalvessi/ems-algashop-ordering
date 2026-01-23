package com.algaworks.algashop.ordering.core.domain.model.commons;

import com.algaworks.algashop.ordering.core.domain.model.commons.ZipCode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ZipCodeTest {

    @Test
    void shouldCreateValidZipCode() {
        ZipCode zipCode = new ZipCode("12345");
        assertNotNull(zipCode);
        assertEquals("12345", zipCode.value());
    }

    @Test
    void shouldNotCreateZipCodeWithNullValue() {
        assertThrows(NullPointerException.class, () ->
            new ZipCode(null)
        );
    }

    @Test
    void shouldNotCreateZipCodeWithBlankValue() {
        assertThrows(IllegalArgumentException.class, () ->
            new ZipCode("   ")
        );
    }

    @Test
    void shouldNotCreateZipCodeWithInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () ->
            new ZipCode("1234")
        );
        assertThrows(IllegalArgumentException.class, () ->
            new ZipCode("123456")
        );
    }

    @Test
    void shouldHaveCorrectStringRepresentation() {
        ZipCode zipCode = new ZipCode("12345");
        assertEquals("12345", zipCode.toString());
    }
}
