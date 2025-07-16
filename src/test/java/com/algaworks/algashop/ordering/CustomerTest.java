package com.algaworks.algashop.ordering;

import com.algaworks.algashop.ordering.domain.entity.Customer;
import com.algaworks.algashop.ordering.domain.utility.IdGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CustomerTest {

    @Test
    public void testingCustomer() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "John Wick",
                LocalDate.of(1993, 6, 28),
                "customer@email.com",
                "123-456-789",
                "12345",
                false,
                OffsetDateTime.now()
        );

        System.out.println(UUID.randomUUID());
        System.out.println(customer.id());
        System.out.println(IdGenerator.generateTimeBasedUUID());

        customer.addLoyaltyPoints(10);
        customer.changeEmail("john@wick.com");
    }
}
