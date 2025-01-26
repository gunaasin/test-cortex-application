package com.clone.amazon.cart;

public record CartUpdateRequestDTO(
        int cartItemId,
        int deliveryOptionId,
        String token,
        String email
) {
}
