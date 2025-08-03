package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingInfoTest {

    @Test
    void shouldCreateWithValidInfo() {
        ShippingInfo info = ShippingInfo.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .address(Address.builder()
                        .street("Street Test")
                        .number("123")
                        .complement("Apt 1")
                        .neighborhood("Test Neighborhood")
                        .city("Test City")
                        .state("Test State")
                        .zipCode(new ZipCode("12345"))
                        .build())
                .build();

        assertNotNull(info);
        assertEquals("John", info.fullName().firstName());
        assertEquals("Doe", info.fullName().lastName());
        assertEquals("12345678900", info.document().value());
        assertEquals("1234567890", info.phone().value());
        assertNotNull(info.address());
    }

    @Test
    void shouldThrowExceptionForNullFullName() {
        ShippingInfo.ShippingInfoBuilder builder = ShippingInfo.builder()
                .fullName(null)
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .address(Address.builder()
                        .street("Street Test")
                        .number("123")
                        .complement("Apt 1")
                        .neighborhood("Test Neighborhood")
                        .city("Test City")
                        .state("Test State")
                        .zipCode(new ZipCode("12345"))
                        .build());

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    void shouldThrowExceptionForNullDocument() {
        ShippingInfo.ShippingInfoBuilder builder = ShippingInfo.builder()
                .fullName(new FullName("John", "Doe"))
                .document(null)
                .phone(new Phone("1234567890"))
                .address(Address.builder()
                        .street("Street Test")
                        .number("123")
                        .complement("Apt 1")
                        .neighborhood("Test Neighborhood")
                        .city("Test City")
                        .state("Test State")
                        .zipCode(new ZipCode("12345"))
                        .build());

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    void shouldThrowExceptionForNullPhone() {
        ShippingInfo.ShippingInfoBuilder builder = ShippingInfo.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(null)
                .address(Address.builder()
                        .street("Street Test")
                        .number("123")
                        .complement("Apt 1")
                        .neighborhood("Test Neighborhood")
                        .city("Test City")
                        .state("Test State")
                        .zipCode(new ZipCode("12345"))
                        .build());

        assertThrows(NullPointerException.class, builder::build);
    }

    @Test
    void shouldThrowExceptionForNullAddress() {
        ShippingInfo.ShippingInfoBuilder builder = ShippingInfo.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .address(null);

        assertThrows(NullPointerException.class, builder::build);
    }
}
