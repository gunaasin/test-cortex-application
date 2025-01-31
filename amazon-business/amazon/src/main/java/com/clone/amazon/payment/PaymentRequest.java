package com.clone.amazon.payment;

import lombok.Builder;

@Builder
public record PaymentRequest(
        String token,
        String email,
        Long amount,
        Long quantity,
        String name,
        String currency
) {
}
