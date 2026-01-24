package com.algaworks.algashop.ordering.core.ports.input.customer;

import java.util.UUID;

public interface ForConfirmCustomerRegistration {
    void confirm(UUID customerId);
}
