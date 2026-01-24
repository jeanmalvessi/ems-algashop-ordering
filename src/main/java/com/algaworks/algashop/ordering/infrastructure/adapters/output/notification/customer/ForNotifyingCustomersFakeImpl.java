package com.algaworks.algashop.ordering.infrastructure.adapters.output.notification.customer;

import com.algaworks.algashop.ordering.core.ports.output.customer.ForNotifyingCustomers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForNotifyingCustomersFakeImpl implements ForNotifyingCustomers {

    @Override
    public void notifyNewRegistration(NotifyNewRegistrationInput input) {
        log.info("Welcome {}", input.firstName());
        log.info("Use your email [{}] to access your account", input.email());
    }
}
