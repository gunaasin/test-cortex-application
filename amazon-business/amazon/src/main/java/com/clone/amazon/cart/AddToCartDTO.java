package com.clone.amazon.cart;

public record AddToCartDTO(
       int productId,
       int quantity,
       String token,
       String email
) {
}
