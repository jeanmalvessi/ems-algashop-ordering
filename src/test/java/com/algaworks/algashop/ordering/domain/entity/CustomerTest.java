package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {

    @Test
    void given_invalidEmail_whenTryCreateCustomer_shouldGenerateException() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Customer customer = new Customer(
                            IdGenerator.generateTimeBasedUUID(),
                            "Jhon Doe",
                            LocalDate.of(1991, 7, 5),
                            "invalid",
                            "478-256-2504",
                            "255-08-0578",
                            false,
                            OffsetDateTime.now()
                    );
                });
    }

    @Test
    void given_invalidEmail_whenTryUpdateCustomerEmail_shouldGenerateException() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Jhon Doe",
                LocalDate.of(1991, 7, 5),
                "jhon.doe@gmail.com",
                "478-256-2504",
                "255-08-0578",
                false,
                OffsetDateTime.now()
        );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    customer.changeEmail("invalid");
                });
    }

    @Test
    void given_unarchivedCustomer_whenArchive_shouldAnonymize() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Jhon Doe",
                LocalDate.of(1991, 7, 5),
                "jhon.doe@gmail.com",
                "478-256-2504",
                "255-08-0578",
                false,
                OffsetDateTime.now()
        );

        customer.archive();

        Assertions.assertWith(customer,
            c -> Assertions.assertThat(c.fullName()).isEqualTo("Anonymous"),
            c -> Assertions.assertThat(c.email()).isNotEqualTo("jhon.doe@gmail.com"),
            c -> Assertions.assertThat(c.phone()).isEqualTo("000-000-0000"),
            c -> Assertions.assertThat(c.document()).isEqualTo("000-00-0000"),
            c -> Assertions.assertThat(c.birthDate()).isNull(),
            c -> Assertions.assertThat(c.isPromotionNotificationsAllowed()).isFalse()
        );
    }

    @Test
    void given_archivedCustomer_whenTryToUpdate_shouldGenerateException () {
        Customer customer = new Customer(
            IdGenerator.generateTimeBasedUUID(),
            "Anonymous",
            null,
            "anonymous@anonymous.com",
            "000-000-0000",
            "000-00-0000",
            false,
            true,
            OffsetDateTime.now(),
            OffsetDateTime.now(),
            0
        );

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::archive);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::enablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::disablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changeEmail("email@gmail.com"));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changePhone("123-123-1234"));
    }

    @Test
    void given_brandNewCustomer_whenAddLoyaltyPoints_shouldSumPoints() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Jhon Doe",
                LocalDate.of(1991, 7, 5),
                "jhon.doe@gmail.com",
                "478-256-2504",
                "255-08-0578",
                false,
                OffsetDateTime.now()
        );

        customer.addLoyaltyPoints(10);
        customer.addLoyaltyPoints(20);

        Assertions.assertThat(customer.loyaltyPoints()).isEqualTo(30);
    }

    @Test
    void given_brandNewCustomer_whenAddInvalidLoyaltyPoints_shouldGenerateException() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Jhon Doe",
                LocalDate.of(1991, 7, 5),
                "jhon.doe@gmail.com",
                "478-256-2504",
                "255-08-0578",
                false,
                OffsetDateTime.now()
        );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> customer.addLoyaltyPoints(0));

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> customer.addLoyaltyPoints(-10));
    }
}
