package com.algaworks.algashop.ordering.infrastructure.adapters.input.web.exception;

public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException() {
    }

    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnprocessableEntityException(String message) {
        super(message);
    }
}
