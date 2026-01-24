package com.algaworks.algashop.ordering.core.ports.output.customer;

import com.algaworks.algashop.ordering.core.ports.input.customer.CustomerFilter;
import com.algaworks.algashop.ordering.core.ports.input.customer.CustomerOutput;
import com.algaworks.algashop.ordering.core.ports.input.customer.CustomerSummaryOutput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ForObtainingCustomers {
    CustomerOutput findById(UUID customerId);
    Page<CustomerSummaryOutput> filter(CustomerFilter filter);
}
