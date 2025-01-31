package com.clone.amazon.payment;

import lombok.Builder;

@Builder
public record PaymentResponse(
        String status,
        String message,
        String sessionId,
        String sessionUrl
) {
}
