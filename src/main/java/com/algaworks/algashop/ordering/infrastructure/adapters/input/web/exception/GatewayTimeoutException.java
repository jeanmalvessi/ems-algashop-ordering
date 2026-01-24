package com.algaworks.algashop.ordering.infrastructure.adapters.input.web.exception;

public class GatewayTimeoutException extends RuntimeException {
    
    public GatewayTimeoutException() {
    }
    
    public GatewayTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
