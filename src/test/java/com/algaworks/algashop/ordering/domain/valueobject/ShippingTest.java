package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ShippingTest {

    @Test
    @DisplayName("Should create shipping with valid data")
    void shouldCreateShippingWithValidData() {
        Recipient recipient = Recipient.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .build();

        Address address = Address.builder()
                .street("Street Test")
                .number("123")
                .complement("Apt 1")
                .neighborhood("Test Neighborhood")
                .city("Test City")
                .state("Test State")
                .zipCode(new ZipCode("12345"))
                .build();

        Shipping shipping = Shipping.builder()
                .cost(new Money("15.90"))
                .expectedDate(LocalDate.now().plusDays(5))
                .recipient(recipient)
                .address(address)
                .build();

        assertThat(shipping).isNotNull();
        assertThat(shipping.cost()).isEqualTo(new Money("15.90"));
        assertThat(shipping.expectedDate()).isNotNull();
        assertThat(shipping.recipient().fullName()).isEqualTo(new FullName("John", "Doe"));
        assertThat(shipping.recipient().document()).isEqualTo(new Document("12345678900"));
        assertThat(shipping.recipient().phone()).isEqualTo(new Phone("1234567890"));
        assertThat(shipping.address()).isNotNull();
    }

    @Test
    @DisplayName("Should not create shipping with null cost")
    void shouldNotCreateShippingWithNullCost() {
        Recipient recipient = Recipient.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .build();

        Address address = Address.builder()
                .street("Street Test")
                .number("123")
                .complement("Apt 1")
                .neighborhood("Test Neighborhood")
                .city("Test City")
                .state("Test State")
                .zipCode(new ZipCode("12345"))
                .build();

        assertThatNullPointerException().isThrownBy(() ->
            Shipping.builder()
                .cost(null)
                .expectedDate(LocalDate.now().plusDays(5))
                .recipient(recipient)
                .address(address)
                .build()
        );
    }

    @Test
    @DisplayName("Should not create shipping with null expected date")
    void shouldNotCreateShippingWithNullExpectedDate() {
        Recipient recipient = Recipient.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .build();

        Address address = Address.builder()
                .street("Street Test")
                .number("123")
                .complement("Apt 1")
                .neighborhood("Test Neighborhood")
                .city("Test City")
                .state("Test State")
                .zipCode(new ZipCode("12345"))
                .build();

        assertThatNullPointerException().isThrownBy(() ->
            Shipping.builder()
                .cost(new Money("15.90"))
                .expectedDate(null)
                .recipient(recipient)
                .address(address)
                .build()
        );
    }

    @Test
    @DisplayName("Should not create shipping with null recipient")
    void shouldNotCreateShippingWithNullRecipient() {
        Address address = Address.builder()
                .street("Street Test")
                .number("123")
                .complement("Apt 1")
                .neighborhood("Test Neighborhood")
                .city("Test City")
                .state("Test State")
                .zipCode(new ZipCode("12345"))
                .build();

        assertThatNullPointerException().isThrownBy(() ->
            Shipping.builder()
                .cost(new Money("15.90"))
                .expectedDate(LocalDate.now().plusDays(5))
                .recipient(null)
                .address(address)
                .build()
        );
    }

    @Test
    @DisplayName("Should not create shipping with null address")
    void shouldNotCreateShippingWithNullAddress() {
        Recipient recipient = Recipient.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .build();

        assertThatNullPointerException().isThrownBy(() ->
            Shipping.builder()
                .cost(new Money("15.90"))
                .expectedDate(LocalDate.now().plusDays(5))
                .recipient(recipient)
                .address(null)
                .build()
        );
    }
}
