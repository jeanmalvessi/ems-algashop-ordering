package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.valueobject.*;
import com.algaworks.algashop.ordering.domain.valueobject.id.CustomerId;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class CustomerTestDataBuilder {

    private CustomerTestDataBuilder() {
    }

    public static Customer.BrandNewCustomerBuilder brandNewCustomer() {
        return Customer.brandNew()
                .fullName(new FullName("John", "Doe"))
                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                .email(new Email("john.doe@gmail.com"))
                .phone(new Phone("478-256-2504"))
                .document(new Document("255-08-0578"))
                .promotionNotificationsAllowed(false)
                .address(Address.builder()
                        .street("Main St")
                        .number("123")
                        .neighborhood("Downtown")
                        .city("Springfield")
                        .state("IL")
                        .zipCode(new ZipCode("62701"))
                        .complement("Apt 1")
                        .build());
    }

    public static Customer.ExistingCustomerBuilder existingCustomer() {
        return Customer.existing()
                .id(new CustomerId())
                .registeredAt(OffsetDateTime.now())
                .promotionNotificationsAllowed(true)
                .archived(false)
                .archivedAt(null)
                .fullName(new FullName("John", "Doe"))
                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                .email(new Email("john.doe@gmail.com"))
                .phone(new Phone("478-256-2504"))
                .document(new Document("255-08-0578"))
                .loyaltyPoints(LoyaltyPoints.ZERO)
                .address(Address.builder()
                        .street("Main St")
                        .number("123")
                        .neighborhood("Downtown")
                        .city("Springfield")
                        .state("IL")
                        .zipCode(new ZipCode("62701"))
                        .complement("Apt 1")
                        .build());
    }

    public static Customer.ExistingCustomerBuilder existingAnonymizedCustomer() {
        return Customer.existing()
                .id(new CustomerId())
                .fullName(new FullName("Anonymous", "Anonymous"))
                .birthDate(null)
                .email(new Email("anonymous@anonymous.com"))
                .phone(new Phone("000-000-0000"))
                .document(new Document("000-00-0000"))
                .promotionNotificationsAllowed(false)
                .archived(true)
                .registeredAt(OffsetDateTime.now())
                .archivedAt(OffsetDateTime.now())
                .loyaltyPoints(new LoyaltyPoints(10))
                .address(Address.builder()
                        .street("Main St")
                        .number("123")
                        .neighborhood("Downtown")
                        .city("Springfield")
                        .state("IL")
                        .zipCode(new ZipCode("62701"))
                        .complement("Apt 1")
                        .build());
    }
}
