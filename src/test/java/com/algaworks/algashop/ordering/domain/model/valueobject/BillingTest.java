package com.algaworks.algashop.ordering.domain.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class BillingTest {

    @Test
    @DisplayName("Should create billing with valid data")
    void shouldCreateBillingWithValidData() {
        var billing = Billing.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .email(new Email("john.doe@example.com"))
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

        assertThat(billing).isNotNull();
        assertThat(billing.fullName()).isEqualTo(new FullName("John", "Doe"));
        assertThat(billing.document()).isEqualTo(new Document("12345678900"));
        assertThat(billing.phone()).isEqualTo(new Phone("1234567890"));
        assertThat(billing.email()).isEqualTo(new Email("john.doe@example.com"));
        assertThat(billing.address()).isNotNull();
        assertThat(billing.address().street()).isEqualTo("Street Test");
    }

    @Test
    @DisplayName("Should not create billing with null full name")
    void shouldNotCreateBillingWithNullFullName() {
        assertThatNullPointerException().isThrownBy(() ->
            Billing.builder()
                .fullName(null)
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .email(new Email("john.doe@example.com"))
                .address(Address.builder()
                        .street("Street Test")
                        .number("123")
                        .complement("Apt 1")
                        .neighborhood("Test Neighborhood")
                        .city("Test City")
                        .state("Test State")
                        .zipCode(new ZipCode("12345"))
                        .build())
                .build()
        );
    }

    @Test
    @DisplayName("Should not create billing with null document")
    void shouldNotCreateBillingWithNullDocument() {
        assertThatNullPointerException().isThrownBy(() ->
            Billing.builder()
                .fullName(new FullName("John", "Doe"))
                .document(null)
                .phone(new Phone("1234567890"))
                .email(new Email("john.doe@example.com"))
                .address(Address.builder()
                        .street("Street Test")
                        .number("123")
                        .complement("Apt 1")
                        .neighborhood("Test Neighborhood")
                        .city("Test City")
                        .state("Test State")
                        .zipCode(new ZipCode("12345"))
                        .build())
                .build()
        );
    }

    @Test
    @DisplayName("Should not create billing with null phone")
    void shouldNotCreateBillingWithNullPhone() {
        assertThatNullPointerException().isThrownBy(() ->
            Billing.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(null)
                .email(new Email("john.doe@example.com"))
                .address(Address.builder()
                        .street("Street Test")
                        .number("123")
                        .complement("Apt 1")
                        .neighborhood("Test Neighborhood")
                        .city("Test City")
                        .state("Test State")
                        .zipCode(new ZipCode("12345"))
                        .build())
                .build()
        );
    }

    @Test
    @DisplayName("Should not create billing with null email")
    void shouldNotCreateBillingWithNullEmail() {
        assertThatNullPointerException().isThrownBy(() ->
            Billing.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .email(null)
                .address(Address.builder()
                        .street("Street Test")
                        .number("123")
                        .complement("Apt 1")
                        .neighborhood("Test Neighborhood")
                        .city("Test City")
                        .state("Test State")
                        .zipCode(new ZipCode("12345"))
                        .build())
                .build()
        );
    }

    @Test
    @DisplayName("Should not create billing with null address")
    void shouldNotCreateBillingWithNullAddress() {
        assertThatNullPointerException().isThrownBy(() ->
            Billing.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .email(new Email("john.doe@example.com"))
                .address(null)
                .build()
        );
    }
}
