package com.algaworks.algashop.ordering.domain.model.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void shouldCreateValidAddress() {
        Address address = Address.builder()
            .street("Maple Street")
            .complement("Apt 101")
            .neighborhood("Downtown")
            .number("123")
            .city("New York")
            .state("NY")
            .zipCode(new ZipCode("12345"))
            .build();

        assertNotNull(address);
        assertEquals("Maple Street", address.street());
        assertEquals("Apt 101", address.complement());
        assertEquals("Downtown", address.neighborhood());
        assertEquals("123", address.number());
        assertEquals("New York", address.city());
        assertEquals("NY", address.state());
    }

    @Test
    void shouldNotCreateAddressWithNullStreet() {
        assertThrows(NullPointerException.class, () ->
            Address.builder()
                .street(null)
                .complement("Apt 101")
                .neighborhood("Downtown")
                .number("123")
                .city("New York")
                .state("NY")
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldNotCreateAddressWithBlankStreet() {
        assertThrows(IllegalArgumentException.class, () ->
            Address.builder()
                .street("  ")
                .complement("Apt 101")
                .neighborhood("Downtown")
                .number("123")
                .city("New York")
                .state("NY")
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldCreateAddressWithNullComplement() {
        Address address = Address.builder()
            .street("Maple Street")
            .complement(null)
            .neighborhood("Downtown")
            .number("123")
            .city("New York")
            .state("NY")
            .zipCode(new ZipCode("12345"))
            .build();

        assertNotNull(address);
        assertNull(address.complement());
    }

    @Test
    void shouldNotCreateAddressWithNullNeighborhood() {
        assertThrows(NullPointerException.class, () ->
            Address.builder()
                .street("Maple Street")
                .complement("Apt 101")
                .neighborhood(null)
                .number("123")
                .city("New York")
                .state("NY")
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldNotCreateAddressWithBlankNeighborhood() {
        assertThrows(IllegalArgumentException.class, () ->
            Address.builder()
                .street("Maple Street")
                .complement("Apt 101")
                .neighborhood("  ")
                .number("123")
                .city("New York")
                .state("NY")
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldNotCreateAddressWithNullNumber() {
        assertThrows(NullPointerException.class, () ->
            Address.builder()
                .street("Maple Street")
                .complement("Apt 101")
                .neighborhood("Downtown")
                .number(null)
                .city("New York")
                .state("NY")
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldNotCreateAddressWithBlankNumber() {
        assertThrows(IllegalArgumentException.class, () ->
            Address.builder()
                .street("Maple Street")
                .complement("Apt 101")
                .neighborhood("Downtown")
                .number("  ")
                .city("New York")
                .state("NY")
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldNotCreateAddressWithNullCity() {
        assertThrows(NullPointerException.class, () ->
            Address.builder()
                .street("Maple Street")
                .complement("Apt 101")
                .neighborhood("Downtown")
                .number("123")
                .city(null)
                .state("NY")
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldNotCreateAddressWithBlankCity() {
        assertThrows(IllegalArgumentException.class, () ->
            Address.builder()
                .street("Maple Street")
                .complement("Apt 101")
                .neighborhood("Downtown")
                .number("123")
                .city("  ")
                .state("NY")
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldNotCreateAddressWithNullState() {
        assertThrows(NullPointerException.class, () ->
            Address.builder()
                .street("Maple Street")
                .complement("Apt 101")
                .neighborhood("Downtown")
                .number("123")
                .city("New York")
                .state(null)
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldNotCreateAddressWithBlankState() {
        assertThrows(IllegalArgumentException.class, () ->
            Address.builder()
                .street("Maple Street")
                .complement("Apt 101")
                .neighborhood("Downtown")
                .number("123")
                .city("New York")
                .state("  ")
                .zipCode(new ZipCode("12345"))
                .build()
        );
    }

    @Test
    void shouldNotCreateAddressWithNullZipCode() {
        assertThrows(NullPointerException.class, () ->
            Address.builder()
                .street("Maple Street")
                .complement("Apt 101")
                .neighborhood("Downtown")
                .number("123")
                .city("New York")
                .state("NY")
                .zipCode(null)
                .build()
        );
    }
}
