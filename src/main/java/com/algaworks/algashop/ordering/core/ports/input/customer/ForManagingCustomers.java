package com.algaworks.algashop.ordering.core.ports.input.customer;

import java.util.UUID;

public interface ForManagingCustomers {
    UUID create(UUID customerAuthenticatedUserId, CustomerInput input);
    void update(UUID rawCustomerId, CustomerUpdateInput input);
    void archive(UUID rawCustomerId);
    void changeEmail(UUID rawCustomerId, String newEmail);
}
