package com.algaworks.algashop.ordering.core.ports.input.customer;

import com.algaworks.algashop.ordering.core.ports.input.commons.AddressData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUpdateInput {
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean promotionNotificationsAllowed;
    private AddressData address;
}
