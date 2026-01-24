package com.algaworks.algashop.ordering.infrastructure.adapters.input.listener.customer;

import com.algaworks.algashop.ordering.core.application.AbstractApplicationIT;
import com.algaworks.algashop.ordering.core.domain.model.customer.*;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderId;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderReadyEvent;
import com.algaworks.algashop.ordering.core.ports.input.customer.ForAddingCustomerLoyaltyPoints;
import com.algaworks.algashop.ordering.core.ports.output.customer.ForNotifyingCustomers;
import com.algaworks.algashop.ordering.core.ports.output.customer.ForNotifyingCustomers.NotifyNewRegistrationInput;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.OffsetDateTime;
import java.util.UUID;

class CustomerEventListenerIT extends AbstractApplicationIT {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private Customers customers;

    @MockitoSpyBean
    private CustomerEventListener customerEventListener;

    @MockitoBean
    private ForAddingCustomerLoyaltyPoints forAddingCustomerLoyaltyPoints;

    @MockitoSpyBean
    private ForNotifyingCustomers forNotifyingCustomers;

    @Test
    void shouldListenOrderReadyEvent() {
        applicationEventPublisher.publishEvent(
                new OrderReadyEvent(
                        new OrderId(),
                        CustomerTestDataBuilder.DEFAULT_CUSTOMER_ID,
                        OffsetDateTime.now()
                )
        );

        Mockito.verify(customerEventListener).listen(Mockito.any(OrderReadyEvent.class));

        Mockito.verify(forAddingCustomerLoyaltyPoints)
                .addLoyaltyPoints(Mockito.any(UUID.class), Mockito.any(String.class));
    }

    @Test
    void shouldListenCustomerArchivedEvent() {
        applicationEventPublisher.publishEvent(
                new CustomerArchivedEvent(
                        CustomerTestDataBuilder.DEFAULT_CUSTOMER_ID,
                        OffsetDateTime.now()
                )
        );

        Mockito.verify(customerEventListener).listen(Mockito.any(CustomerArchivedEvent.class));
    }

    @Test
    void shouldListenCustomerRegisteredEvent() {
        Customer customer = CustomerTestDataBuilder.brandNewCustomer().build();
        customers.add(customer);

        /*applicationEventPublisher.publishEvent(
                new CustomerRegisteredEvent(
                        customer.id(),
                        OffsetDateTime.now(),
                        new FullName("John", "Doe"),
                        new Email("john.doe@email.com")
                )
        );*/

        Mockito.verify(customerEventListener).listen(Mockito.any(CustomerRegisteredEvent.class));

        Mockito.verify(forNotifyingCustomers)
                .notifyNewRegistration(Mockito.any(NotifyNewRegistrationInput.class));
    }
}
