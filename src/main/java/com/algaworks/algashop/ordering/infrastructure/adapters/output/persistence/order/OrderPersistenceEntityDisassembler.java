package com.algaworks.algashop.ordering.infrastructure.adapters.output.persistence.order;

import com.algaworks.algashop.ordering.core.domain.model.commons.Address;
import com.algaworks.algashop.ordering.core.domain.model.commons.Document;
import com.algaworks.algashop.ordering.core.domain.model.commons.Email;
import com.algaworks.algashop.ordering.core.domain.model.commons.FullName;
import com.algaworks.algashop.ordering.core.domain.model.commons.Money;
import com.algaworks.algashop.ordering.core.domain.model.commons.Phone;
import com.algaworks.algashop.ordering.core.domain.model.commons.Quantity;
import com.algaworks.algashop.ordering.core.domain.model.commons.ZipCode;
import com.algaworks.algashop.ordering.core.domain.model.customer.CustomerId;
import com.algaworks.algashop.ordering.core.domain.model.order.Billing;
import com.algaworks.algashop.ordering.core.domain.model.order.CreditCardId;
import com.algaworks.algashop.ordering.core.domain.model.order.Order;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderId;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderItem;
import com.algaworks.algashop.ordering.core.domain.model.order.OrderStatus;
import com.algaworks.algashop.ordering.core.domain.model.order.PaymentMethod;
import com.algaworks.algashop.ordering.core.domain.model.order.Recipient;
import com.algaworks.algashop.ordering.core.domain.model.order.Shipping;
import com.algaworks.algashop.ordering.core.domain.model.product.Product;
import com.algaworks.algashop.ordering.core.domain.model.product.ProductId;
import com.algaworks.algashop.ordering.core.domain.model.product.ProductName;
import com.algaworks.algashop.ordering.infrastructure.persistence.commons.AddressEmbeddable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderPersistenceEntityDisassembler {

    public Order toDomainEntity(OrderPersistenceEntity persistenceEntity) {

        CreditCardId creditCardId = null;
        if (persistenceEntity.getCreditCardId() != null) {
            creditCardId = new CreditCardId(persistenceEntity.getCreditCardId());
        }

        return Order.existing()
                .id(new OrderId(persistenceEntity.getId()))
                .customerId(new CustomerId(persistenceEntity.getCustomerId()))
                .totalAmount(new Money(persistenceEntity.getTotalAmount()))
                .totalItems(new Quantity(persistenceEntity.getTotalItems()))
                .status(OrderStatus.valueOf(persistenceEntity.getStatus()))
                .paymentMethod(PaymentMethod.valueOf(persistenceEntity.getPaymentMethod()))
                .creditCardId(creditCardId)
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
