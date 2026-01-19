package com.algaworks.algashop.ordering.application.customer.management;

import com.algaworks.algashop.ordering.application.AbstractApplicationIT;
import com.algaworks.algashop.ordering.application.customer.notification.CustomerNotificationApplicationService;
import com.algaworks.algashop.ordering.application.customer.query.CustomerOutput;
import com.algaworks.algashop.ordering.application.customer.query.CustomerQueryService;
import com.algaworks.algashop.ordering.domain.model.customer.*;
import com.algaworks.algashop.ordering.infrastructure.listener.customer.CustomerEventListener;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.LocalDate;
import java.util.UUID;

class CustomerManagementApplicationServiceIT extends AbstractApplicationIT {

    @Autowired
    private CustomerManagementApplicationService customerManagementApplicationService;

    @MockitoSpyBean
    private CustomerEventListener customerEventListener;

    @MockitoSpyBean
    private CustomerNotificationApplicationService customerNotificationApplicationService;

    @Autowired
    private CustomerQueryService customerQueryService;

    @Test
    void shouldRegister() {
        CustomerInput input = CustomerInputTestDataBuilder.aCustomer().build();

        UUID customerId = customerManagementApplicationService.create(input);
        Assertions.assertThat(customerId).isNotNull();

        CustomerOutput customerOutput = customerQueryService.findById(customerId);

        Assertions.assertThat(customerOutput)
                .extracting(
                        CustomerOutput::getId,
                        CustomerOutput::getFirstName,
                        CustomerOutput::getLastName,
                        CustomerOutput::getEmail,
                        CustomerOutput::getBirthDate
                ).containsExactly(
                        customerId,
                        "John",
                        "Doe",
                        "johndoe@email.com",
                        LocalDate.of(1991, 7,5)
                );

        Assertions.assertThat(customerOutput.getRegisteredAt()).isNotNull();

        Mockito.verify(customerEventListener).listen(Mockito.any(CustomerRegisteredEvent.class));
        Mockito.verify(customerEventListener, Mockito.never()).listen(Mockito.any(CustomerArchivedEvent.class));

        Mockito.verify(customerNotificationApplicationService).notifyNewRegistration(
                Mockito.any(CustomerNotificationApplicationService.NotifyNewRegistrationInput.class));
    }

    @Test
    void shouldUpdate() {
        CustomerInput input = CustomerInputTestDataBuilder.aCustomer().build();
        CustomerUpdateInput updateInput = CustomerUpdateInputTestDataBuilder.aCustomerUpdate().build();

        UUID customerId = customerManagementApplicationService.create(input);
        Assertions.assertThat(customerId).isNotNull();

        customerManagementApplicationService.update(customerId, updateInput);

        CustomerOutput customerOutput = customerQueryService.findById(customerId);

        Assertions.assertThat(customerOutput)
                .extracting(
                        CustomerOutput::getId,
                        CustomerOutput::getFirstName,
                        CustomerOutput::getLastName,
                        CustomerOutput::getEmail,
                        CustomerOutput::getBirthDate
                ).containsExactly(
                        customerId,
                        "Matt",
                        "Damon",
                        "johndoe@email.com",
                        LocalDate.of(1991, 7,5)
                );

        Assertions.assertThat(customerOutput.getRegisteredAt()).isNotNull();
    }

    @Test
    void shouldArchiveCustomer() {
        CustomerInput input = CustomerInputTestDataBuilder.aCustomer().build();
        UUID customerId = customerManagementApplicationService.create(input);
        Assertions.assertThat(customerId).isNotNull();

        customerManagementApplicationService.archive(customerId);

        CustomerOutput archivedCustomer = customerQueryService.findById(customerId);

        Assertions.assertThat(archivedCustomer)
                .isNotNull()
                .extracting(
                        CustomerOutput::getFirstName,
                        CustomerOutput::getLastName,
                        CustomerOutput::getPhone,
                        CustomerOutput::getDocument,
                        CustomerOutput::getBirthDate,
                        CustomerOutput::getPromotionNotificationsAllowed
                ).containsExactly(
                        "Anonymous",
                        "Anonymous",
                        "000-000-0000",
                        "000-00-0000",
                        null,
                        false
                );

        Assertions.assertThat(archivedCustomer.getEmail()).endsWith("@anonymous.com");
        Assertions.assertThat(archivedCustomer.getArchived()).isTrue();
        Assertions.assertThat(archivedCustomer.getArchivedAt()).isNotNull();
        Assertions.assertThat(archivedCustomer.getAddress()).isNotNull();
        Assertions.assertThat(archivedCustomer.getAddress().getNumber()).isNotNull().isEqualTo("00000");
        Assertions.assertThat(archivedCustomer.getAddress().getComplement()).isNotNull().isEqualTo("Anonymous");

        Mockito.verify(customerEventListener).listen(Mockito.any(CustomerArchivedEvent.class));
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionWhenArchivingNonExistingCustomer() {
        UUID nonExistingId = UUID.randomUUID();

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> customerManagementApplicationService.archive(nonExistingId));
    }

    @Test
    void shouldThrowCustomerArchivedExceptionWhenArchivingAlreadyArchivedCustomer() {
        CustomerInput input = CustomerInputTestDataBuilder.aCustomer().build();
        UUID customerId = customerManagementApplicationService.create(input);
        Assertions.assertThat(customerId).isNotNull();

        customerManagementApplicationService.archive(customerId);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customerManagementApplicationService.archive(customerId));
    }

    @Test
    void shouldChangeEmail() {
        CustomerInput customerInput = CustomerInputTestDataBuilder.aCustomer().build();

        UUID customerId = customerManagementApplicationService.create(customerInput);

        Assertions.assertThat(customerId).isNotNull();

        String newEmail = "new-email@example.com";
        customerManagementApplicationService.changeEmail(customerId, newEmail);

        CustomerOutput customerOutput = customerQueryService.findById(customerId);

        Assertions.assertThat(customerOutput).satisfies(
                co -> Assertions.assertThat(co.getId()).isEqualTo(customerId),
                co -> Assertions.assertThat(co.getEmail()).isEqualTo(newEmail)
        );
    }

    @Test
    void shouldThrowExceptionWhenTryToChangeEmailOfNonExistentCustomer() {
        UUID customerId = UUID.randomUUID();

        String newEmail = "new-email@example.com";

        Assertions.assertThatThrownBy(() -> customerManagementApplicationService.changeEmail(customerId, newEmail))
                .isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void shouldThrowExceptionWhenTryToChangeEmailOfArchivedCustomer() {
        CustomerInput customerInput = CustomerInputTestDataBuilder.aCustomer().build();

        UUID customerId = customerManagementApplicationService.create(customerInput);

        Assertions.assertThat(customerId).isNotNull();

        customerManagementApplicationService.archive(customerId);

        String newEmail = "new-email@example.com";

        Assertions.assertThatThrownBy(() -> customerManagementApplicationService.changeEmail(customerId, newEmail))
                .isInstanceOf(CustomerArchivedException.class);
    }

    @Test
    void shouldThrowExceptionWhenTryToChangeEmailWithInvalidEmail() {
        CustomerInput customerInput = CustomerInputTestDataBuilder.aCustomer().build();

        UUID customerId = customerManagementApplicationService.create(customerInput);

        Assertions.assertThat(customerId).isNotNull();

        String newEmail = "invalid-email";

        Assertions.assertThatThrownBy(() -> customerManagementApplicationService.changeEmail(customerId, newEmail))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowExceptionWhenTryToChangeEmailUsingAlreadyExistingEmail() {
        CustomerInput customerInput1 = CustomerInputTestDataBuilder.aCustomer().build();
        CustomerInput customerInput2 = CustomerInputTestDataBuilder.aCustomer().email("customer2@email.com").build();

        UUID customerId1 = customerManagementApplicationService.create(customerInput1);
        UUID customerId2 = customerManagementApplicationService.create(customerInput2);

        Assertions.assertThat(customerId1).isNotNull();
        Assertions.assertThat(customerId2).isNotNull();

        String newEmail = customerInput2.getEmail();

        Assertions.assertThatThrownBy(() -> customerManagementApplicationService.changeEmail(customerId1, newEmail))
                .isInstanceOf(CustomerEmailIsInUseException.class);

        CustomerOutput customerOutput = customerQueryService.findById(customerId1);

        Assertions.assertThat(customerOutput).satisfies(
                co -> Assertions.assertThat(co.getId()).isEqualTo(customerId1),
                co -> Assertions.assertThat(co.getEmail()).isEqualTo(customerInput1.getEmail())
        );
    }
}
