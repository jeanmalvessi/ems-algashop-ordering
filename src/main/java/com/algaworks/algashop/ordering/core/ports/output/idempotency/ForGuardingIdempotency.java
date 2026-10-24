package com.algaworks.algashop.ordering.core.ports.output.idempotency;

import java.util.UUID;

public interface ForGuardingIdempotency {
    boolean runOnce(UUID idempotencyKey, Runnable work);
}
