package com.algaworks.algashop.ordering.core.ports.input.customer;

import java.util.UUID;

public interface ForAddingCustomerLoyaltyPoints {
    void addLoyaltyPoints(UUID rawCustomerId, String rawOrderId);
}
