package com.algaworks.algashop.ordering.infrastructure.persistence.order;

import com.algaworks.algashop.ordering.domain.model.commons.*;
import com.algaworks.algashop.ordering.domain.model.order.*;
import com.algaworks.algashop.ordering.domain.model.product.Product;
import com.algaworks.algashop.ordering.domain.model.product.ProductName;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerId;
import com.algaworks.algashop.ordering.domain.model.product.ProductId;
import com.algaworks.algashop.ordering.infrastructure.persistence.commons.AddressEmbeddable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderPersistenceEntityDisassembler {

    public Order toDomainEntity(OrderPersistenceEntity persistenceEntity) {
        return Order.existing()
                .id(new OrderId(persistenceEntity.getId()))
                .customerId(new CustomerId(persistenceEntity.getCustomerId()))
                .totalAmount(new Money(persistenceEntity.getTotalAmount()))
                .totalItems(new Quantity(persistenceEntity.getTotalItems()))
                .status(OrderStatus.valueOf(persistenceEntity.getStatus()))
                .paymentMethod(PaymentMethod.valueOf(persistenceEntity.getPaymentMethod()))
                .placedAt(persistenceEntity.getPlacedAt())
                .paidAt(persistenceEntity.getPaidAt())
                .canceledAt(persistenceEntity.getCanceledAt())
                .readyAt(persistenceEntity.getReadyAt())
                .billing(this.buildBilling(persistenceEntity.getBilling()))
                .shipping(this.buildShipping(persistenceEntity.getShipping()))
                .items(this.buildItems(persistenceEntity.getItems()))
                .version(persistenceEntity.getVersion())
                .build();
    }

    private Billing buildBilling(BillingEmbeddable billing) {
        if (Objects.isNull(billing)) {
            return null;
        }

        return Billing.builder()
                .fullName(new FullName(billing.getFirstName(), billing.getLastName()))
                .document(new Document(billing.getDocument()))
                .phone(new Phone(billing.getPhone()))
                .email(new Email(billing.getEmail()))
                .address(this.buildAddress(billing.getAddress()))
                .build();
    }

    private Shipping buildShipping(ShippingEmbeddable shipping) {
        if (Objects.isNull(shipping)) {
            return null;
        }

        return Shipping.builder()
                .cost(new Money(shipping.getCost()))
                .expectedDate(shipping.getExpectedDate())
                .recipient(this.buildRecipient(shipping.getRecipient()))
                .address(this.buildAddress(shipping.getAddress()))
                .build();
    }

    private Address buildAddress(AddressEmbeddable address) {
        Objects.requireNonNull(address);

        return Address.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(new ZipCode(address.getZipCode()))
                .build();
    }

    private Recipient buildRecipient(RecipientEmbeddable recipient) {
        Objects.requireNonNull(recipient);

        return Recipient.builder()
                .fullName(new FullName(recipient.getFirstName(), recipient.getLastName()))
                .document(new Document(recipient.getDocument()))
                .phone(new Phone(recipient.getPhone()))
                .build();
    }

    private Set<OrderItem> buildItems(Set<OrderItemPersistenceEntity> items) {
        if (Objects.isNull(items)) {
            return new HashSet<>();
        }

        return items.stream()
                .map(orderItemPersistence -> OrderItem.brandNew()
                        .orderId(new OrderId(orderItemPersistence.getOrderId()))
                        .product(Product.builder()
                                .id(new ProductId(orderItemPersistence.getProductId()))
                                .name(new ProductName(orderItemPersistence.getProductName()))
                                .price(new Money(orderItemPersistence.getPrice()))
                                .inStock(true)
                                .build()
                        )
                        .quantity(new Quantity(orderItemPersistence.getQuantity()))
                        .build())
                .collect(Collectors.toSet());
    }
}
