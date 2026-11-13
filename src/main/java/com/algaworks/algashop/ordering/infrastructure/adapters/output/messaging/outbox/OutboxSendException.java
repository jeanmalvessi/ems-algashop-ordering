package com.algaworks.algashop.ordering.infrastructure.adapters.output.messaging.outbox;

public class OutboxSendException extends RuntimeException {
    public OutboxSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
