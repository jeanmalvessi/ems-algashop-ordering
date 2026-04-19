package com.algaworks.algashop.ordering.infrastructure.adapters.input.web.exception;

public class BadGatewayException extends RuntimeException {
    
    public BadGatewayException() {
    }

    public BadGatewayException(String message) {
        super(message);
    }

    public BadGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class ServerErrorException extends BadGatewayException {
        public ServerErrorException() {
        }

        public ServerErrorException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class ClientErrorException extends BadGatewayException {
        public ClientErrorException() {
        }

        public ClientErrorException(String message) {
            super(message);
        }

        public ClientErrorException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
