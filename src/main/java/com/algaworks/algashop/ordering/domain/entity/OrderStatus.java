package com.algaworks.algashop.ordering.domain.entity;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

    DRAFT,
    PLACED(DRAFT),
    PAID(PLACED),
    READY(PAID),
    CANCELED(READY, PAID, PLACED, DRAFT);

    OrderStatus(OrderStatus... previousStatuses) {
        this.previousStatuses = Arrays.asList(previousStatuses);
    }

    private final List<OrderStatus> previousStatuses;

    public boolean canChangeTo(OrderStatus newStatus) {
        return newStatus.previousStatuses.contains(this/*currentStatus*/);
    }

    public boolean canNotChangeTo(OrderStatus newStatus) {
        return !canChangeTo(newStatus);
    }
}
