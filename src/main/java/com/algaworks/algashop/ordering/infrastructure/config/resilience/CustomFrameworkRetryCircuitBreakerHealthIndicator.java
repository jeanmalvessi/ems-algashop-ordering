package com.algaworks.algashop.ordering.infrastructure.config.resilience;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.cloud.circuitbreaker.retry.CircuitBreakerRetryPolicy;
import org.springframework.cloud.circuitbreaker.retry.FrameworkRetryCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("circuitBreakers")
public class CustomFrameworkRetryCircuitBreakerHealthIndicator implements HealthIndicator {

    private final List<FrameworkRetryCircuitBreaker> circuitBreakers = new ArrayList<>();

    public CustomFrameworkRetryCircuitBreakerHealthIndicator(CircuitBreakerFactory circuitBreakerFactory) {
        circuitBreakers.add((FrameworkRetryCircuitBreaker) circuitBreakerFactory.create(SpringCircuitBreakerConfig.PRODUCT_CATALOG_CB_ID));
        circuitBreakers.add((FrameworkRetryCircuitBreaker) circuitBreakerFactory.create(SpringCircuitBreakerConfig.RAPIDEX_CB_ID));
    }

    @Override
    public @Nullable Health health() {
        Map<String, Object> details = new HashMap<>();
        String status = "UP";
        Throwable lastException = null;

        for (FrameworkRetryCircuitBreaker circuitBreaker : circuitBreakers) {
            var policy = circuitBreaker.getConfig().getCircuitBreakerRetryPolicy();
            var state = policy.getState();

            Map<String, Object> cbDetails = new HashMap<>();
            cbDetails.put("state", state.name());

            if (CircuitBreakerRetryPolicy.State.OPEN.equals(state)) {
                status = "DEGRADED";
                if (policy.getLastException() != null && policy.getLastException().getCause() != null) {
                    lastException = policy.getLastException().getCause();
                    cbDetails.put("lastException", lastException.getMessage());
                }
            }

            details.put(circuitBreaker.getId(), cbDetails);
        }

        Health.Builder builder = Health.status(status).withDetails(details);

        if (status.equals("DEGRADED") && lastException != null) {
            builder.withException(lastException);
        }

        return builder.build();
    }
}
