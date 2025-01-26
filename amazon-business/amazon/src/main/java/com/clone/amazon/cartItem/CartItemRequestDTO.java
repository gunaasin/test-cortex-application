package com.clone.amazon.cartItem;

import lombok.Builder;

@Builder
public record CartItemRequestDTO(
        int cartId,
        int quantity,
        int productId
) {
}
