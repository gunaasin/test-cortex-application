package com.clone.amazon.cart;

import lombok.Builder;

@Builder
public record CartRequestDTO(
        String token,
        String email
) {
}
