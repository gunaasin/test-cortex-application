package com.clone.amazon.cartItem;

public record RemoveProductDTO(
        int productId,
        String token,
        String email
) {
}
