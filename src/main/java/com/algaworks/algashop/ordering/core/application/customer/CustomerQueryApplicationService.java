package com.algaworks.algashop.ordering.core.application.customer;

import com.algaworks.algashop.ordering.core.ports.input.customer.CustomerFilter;
import com.algaworks.algashop.ordering.core.ports.input.customer.CustomerOutput;
import com.algaworks.algashop.ordering.core.ports.input.customer.CustomerSummaryOutput;
import com.algaworks.algashop.ordering.core.ports.input.customer.ForQueryingCustomers;
import com.algaworks.algashop.ordering.core.ports.output.customer.ForObtainingCustomers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerQueryApplicationService implements ForQueryingCustomers {

    private final ForObtainingCustomers forObtainingCustomers;

    @Override
    public CustomerOutput findById(UUID customerId) {
        return forObtainingCustomers.findById(customerId);
    }

    @Override
    public Page<CustomerSummaryOutput> filter(CustomerFilter filter) {
        return forObtainingCustomers.filter(filter);
    }
}
